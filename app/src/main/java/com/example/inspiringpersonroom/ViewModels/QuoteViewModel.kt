package com.example.inspiringpersonroom.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.inspiringpersonroom.Entity.Person
import com.example.inspiringpersonroom.helpers.PersonDatabase
import com.example.inspiringpersonroom.Entity.Quote
import com.example.inspiringpersonroom.Repository.QuoteRepository

class QuoteViewModel (application: Application): AndroidViewModel(application) {
    private val repository: QuoteRepository
    val newPersonQuotes = MutableLiveData<List<Quote>>()


    companion object{
        private var INSTANCE: QuoteViewModel?= null
        fun getInstance(application: Application): QuoteViewModel{
            if(INSTANCE == null){
                INSTANCE = QuoteViewModel(application)
                return INSTANCE as QuoteViewModel
            }
            return INSTANCE as QuoteViewModel
        }
    }
    init {
        val personsDao = PersonDatabase.getInstance(
            viewModelScope
        ).personDao()
        val quotesDao = PersonDatabase.getInstance(
            viewModelScope
        ).quoteDao()
        repository =
            QuoteRepository(quotesDao)
    }


    fun getQuotesOfPerson(personId: Int):List<Quote>{
        return repository.getQuotesOfPerson(personId)
    }
    fun insertMultiple(person: Person, quotes: List<Quote>){
        for(quote in quotes){
            quote.personId = person.id
            insert(quote)
        }
    }
    fun insert(quote: Quote){
        repository.insert(quote)
    }

    fun deletePersonQuote(personId: Int){
        repository.deletePersonQuotes(personId)
    }
}
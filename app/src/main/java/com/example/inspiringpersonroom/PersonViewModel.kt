package com.example.inspiringpersonroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PersonRepository
    val allPersons : LiveData<List<Person>>
    val editParson = MutableLiveData<Person>()

    init {
        val personsDao = PersonDatabase.getInstance(viewModelScope).personDao()
        repository = PersonRepository((personsDao))
        allPersons = repository.allPersons
    }

    fun insert(person: Person) {
        repository.insert((person))
    }

    fun find(id: Int): Person{
        return repository.find(id)
    }

    fun update(person: Person){
        repository.update(person)
    }
    fun delete(person: Person){
        repository.delete(person)
    }
}
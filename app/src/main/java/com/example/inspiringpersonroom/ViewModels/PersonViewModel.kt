package com.example.inspiringpersonroom.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.inspiringpersonroom.Entity.Person
import com.example.inspiringpersonroom.helpers.PersonDatabase
import com.example.inspiringpersonroom.Repository.PersonRepository

class PersonViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PersonRepository
    val allPersons : LiveData<List<Person>>
    val editParson = MutableLiveData<Person>()

    companion object{
        private var INSTANCE: PersonViewModel?= null
        fun getInstance(application: Application): PersonViewModel{
            if(INSTANCE == null){
                INSTANCE = PersonViewModel(application)
                return INSTANCE as PersonViewModel
            }
            return INSTANCE as PersonViewModel
        }
    }
    init {
        val personsDao = PersonDatabase.getInstance(
            viewModelScope
        ).personDao()
        repository =
            PersonRepository((personsDao))
        allPersons = repository.allPersons
    }

    fun insert(person: Person) {
        repository.insert((person))
    }

    fun find(id: Int): Person {
        return repository.find(id)
    }

    fun update(person: Person){
        repository.update(person)
    }
    fun delete(person: Person){
        repository.delete(person)
    }
    fun findByName(name:String):Person{
        return repository.findByName(name)
    }
}
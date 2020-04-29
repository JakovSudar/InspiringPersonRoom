package com.example.inspiringpersonroom

import androidx.lifecycle.LiveData

class PersonRepository (private val personDao: PersonDao) {
    val allPersons: LiveData<List<Person>> = personDao.getAll()

    fun insert (person : Person){
        personDao.insert(person)
    }

    fun find(id : Int): Person{
        return personDao.find(id)
    }

    fun update(person: Person){
        personDao.update(person)
    }

    fun delete(person: Person){
        personDao.delete(person)
    }

}
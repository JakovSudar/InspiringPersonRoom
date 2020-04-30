package com.example.inspiringpersonroom.Repository

import androidx.lifecycle.LiveData
import com.example.inspiringpersonroom.Entity.Person
import com.example.inspiringpersonroom.DAO.PersonDao

class PersonRepository (private val personDao: PersonDao) {
    val allPersons: LiveData<List<Person>> = personDao.getAll()

    fun insert (person : Person){
        personDao.insert(person)
    }

    fun find(id : Int): Person {
        return personDao.find(id)
    }

    fun update(person: Person){
        personDao.update(person)
    }

    fun delete(person: Person){
        personDao.delete(person)
    }

    fun findByName(name:String):Person{
        return personDao.getByName(name)
    }

}
package com.example.inspiringpersonroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {

    @Insert
    fun insert(person :Person);

    @Delete
    fun delete(person: Person);

    @Update
    fun update(person: Person);

    @Update
    fun updatePerson(vararg person: Person)

    @Query("SELECT * FROM persons")
    fun getAll(): LiveData<List<Person>>

    @Query("SELECT * FROM persons WHERE id = :id")
    fun find(id: Int):Person;
    @Query("DELETE FROM persons WHERE 1=1 ")
    fun deleteAll()

}
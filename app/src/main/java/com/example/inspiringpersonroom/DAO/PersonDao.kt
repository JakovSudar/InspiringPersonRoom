package com.example.inspiringpersonroom.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inspiringpersonroom.Entity.Person

@Dao
interface PersonDao {

    @Insert
    fun insert(person : Person);

    @Delete
    fun delete(person: Person);

    @Update
    fun update(person: Person);

    @Query("SELECT * FROM persons")
    fun getAll(): LiveData<List<Person>>

    @Query("SELECT * FROM persons WHERE name = :name LIMIT 1")
    fun getByName(name: String): Person



    @Query("SELECT * FROM persons WHERE id = :id")
    fun find(id: Int): Person;

    @Query("DELETE FROM persons WHERE 1=1 ")
    fun deleteAll()

}
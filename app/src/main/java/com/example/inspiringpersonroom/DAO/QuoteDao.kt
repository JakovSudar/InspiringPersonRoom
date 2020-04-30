package com.example.inspiringpersonroom.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inspiringpersonroom.Entity.Quote

@Dao
interface QuoteDao {
    @Insert
    fun insert(quote: Quote)

    @Query("SELECT * FROM quotes WHERE personId = :personId")
    fun getQuotesOfPerson(personId: Int) : List<Quote>
}
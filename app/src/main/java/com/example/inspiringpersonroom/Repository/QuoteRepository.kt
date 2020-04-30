package com.example.inspiringpersonroom.Repository

import com.example.inspiringpersonroom.Entity.Quote
import com.example.inspiringpersonroom.DAO.QuoteDao


class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotesOfPerson(personId : Int):List<Quote>{
        return quoteDao.getQuotesOfPerson(personId)
    }

    fun insert(quote: Quote){
        quoteDao.insert(quote)
    }
}
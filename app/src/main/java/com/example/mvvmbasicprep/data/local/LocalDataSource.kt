package com.example.mvvmbasicprep.data.local

import com.example.mvvmbasicprep.data.model.Quote
import io.reactivex.Single

class LocalDataSource private constructor(

    private val quoteDao: QuoteDao) : DbDataSource {

    override fun getQuotes(): Single<List<Quote>> {
        return quoteDao.getAll()
    }

    override fun addQuote(item: Quote): Single<Long> {
        return quoteDao.insert(item)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(quoteDao: QuoteDao) : LocalDataSource {
            if (INSTANCE == null) {
                synchronized(LocalDataSource::javaClass) {
                    INSTANCE = LocalDataSource(quoteDao)
                }
            }

            return INSTANCE!!
        }
    }
}
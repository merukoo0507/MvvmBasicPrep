package com.example.mvvmbasicprep.data.local

import com.example.mvvmbasicprep.data.model.Quote
import io.reactivex.Single

interface DbDataSource {
    fun getQuotes(): Single<List<Quote>>

    fun addQuote(item: Quote): Single<Long>
}
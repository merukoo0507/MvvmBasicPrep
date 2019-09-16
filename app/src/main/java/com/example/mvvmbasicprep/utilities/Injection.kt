package com.example.mvvmbasicprep.utilities

import android.content.Context
import com.example.mvvmbasicprep.data.DataRepository
import com.example.mvvmbasicprep.data.local.LocalDataSource
import com.example.mvvmbasicprep.data.local.QuoteDatabase

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = QuoteDatabase.getInstance(context)
        return DataRepository.get(
            context,
//            RemoteDataSource(context),
            LocalDataSource.getInstance(database.getQuoteDao())
        )
    }
}
package com.example.mvvmbasicprep.data

import android.content.Context
import com.example.mvvmbasicprep.data.local.DbDataSource
import com.example.mvvmbasicprep.data.model.Quote
import io.reactivex.Single

class DataRepository(private val context: Context,
//                     private val remote: ApiDataSource,
                     private val local: DbDataSource) {

    fun getAllQuotes(fromRemote: Boolean): Single<List<Quote>> {
//        return if (fromRemote) {
//            local.getQuotes()
//        } else {
            return local.getQuotes()
//        }
    }

    fun addQuote(item: Quote): Single<Long> {
        return local.addQuote(item)
    }

    companion object {
        private var INSTANCE: DataRepository? = null

        fun get(context: Context,
//                remoteDatabase: ApiDataSource,
                localDataSource: DbDataSource
        ): DataRepository {
            return INSTANCE ?: DataRepository(
                context,
//                remoteDatabase,
                localDataSource)
                .apply { INSTANCE = this }
        }

        fun clear() {
            INSTANCE = null
        }
    }
}
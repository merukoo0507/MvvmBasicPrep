package com.example.mvvmbasicprep.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmbasicprep.data.model.Quote

@Database(entities = [(Quote::class)], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "quote_database"
        private var INSTANCE: QuoteDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): QuoteDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }

                return INSTANCE!!
            }
        }
    }

    abstract fun getQuoteDao(): QuoteDao
}
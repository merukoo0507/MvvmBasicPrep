package com.example.mvvmbasicprep.data.local

import androidx.room.*
import com.example.mvvmbasicprep.data.model.Quote
import io.reactivex.Single

@Dao
interface QuoteDao {
    @Query("select * from quote_table")
    fun getAll(): Single<List<Quote>>

    @Query("select * from quote_table where id LIKE :id LIMIT 1")
    fun queryById(id: Long): Single<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Quote): Single<Long>

    @Update
    fun update(item: Quote): Single<Int>

    @Delete
    fun delete(item: Quote): Single<Int>
}
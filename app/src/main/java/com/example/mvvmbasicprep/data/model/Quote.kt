package com.example.mvvmbasicprep.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class Quote (

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    var author: String,

    var content: String
)
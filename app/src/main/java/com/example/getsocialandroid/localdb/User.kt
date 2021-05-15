package com.example.getsocialandroid.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tUser")
data class User(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val Name: String,
    val Email: String
)
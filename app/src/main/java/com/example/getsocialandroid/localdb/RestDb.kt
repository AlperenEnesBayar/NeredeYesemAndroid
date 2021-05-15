package com.example.getsocialandroid.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tRest")
data class RestDb(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val rest_id: String?
)
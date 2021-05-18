package com.example.getsocialandroid.localdb

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.getsocialandroid.model.Adress
import com.example.getsocialandroid.model.Menu

@Entity(tableName = "tRest")
data class RestDb(
    @PrimaryKey @NonNull val restaurant_id: String,
    val restaurant_name: String?,
    val restaurant_phone: String?,
    val cuisines: String,
    val address: String
)
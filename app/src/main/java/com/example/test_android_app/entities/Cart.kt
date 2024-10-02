package com.example.test_android_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class Cart(

    @PrimaryKey(true)
    val id: Int,

    val name: String,

    val price: Double,

    val quantity: Int,
)
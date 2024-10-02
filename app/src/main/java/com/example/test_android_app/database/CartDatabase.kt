package com.example.test_android_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_android_app.dao.CartDAO
import com.example.test_android_app.entities.Cart

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDAO

    companion object {
        @Volatile
        private var Instance: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CartDatabase::class.java, "cart_database.db")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
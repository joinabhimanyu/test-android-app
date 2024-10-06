package com.example.test_android_app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.test_android_app.entities.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Query("SELECT * FROM carts order by name ASC")
    fun getAllItems():Flow<List<Cart>>

    @Query("select * from carts where id=:id")
    fun getItemById(id: Int):Flow<Cart>

    @Insert(entity = Cart::class)
    suspend fun insertItem(item: Cart)

    @Insert(entity = Cart::class)
    suspend fun insertItems(items: List<Cart>)

    @Update(entity = Cart::class)
    suspend fun updateItem(item: Cart)

    @Update(entity = Cart::class)
    suspend fun updateItems(items: List<Cart>)

    @Delete(entity = Cart::class)
    suspend fun deleteItem(item: Cart)
}
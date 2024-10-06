package com.example.test_android_app.repositories

import android.content.Context
import com.example.test_android_app.dao.CartDAO
import com.example.test_android_app.database.CartDatabase
import com.example.test_android_app.entities.Cart
import com.example.test_android_app.models.ApiResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class CartRepository(context: Context): BaseRoomRepository() {

    private val db: CartDatabase = CartDatabase.getDatabase(context)

    suspend fun getAllItems(): CompletableDeferred<ApiResponse<List<Cart>>> {
        return super.executeCall<List<Cart>>(db.cartDao().getAllItems())
    }

    suspend fun getItemById(id: Int): CompletableDeferred<ApiResponse<Cart>> {
        return super.executeCall<Cart>(db.cartDao().getItemById(id))
    }

    suspend fun insertItem(item: Cart): CompletableDeferred<ApiResponse<String>> {
        return super.executeUpdate(db.cartDao().insertItem(item), "Item inserted successfully")
    }

    suspend fun insertItems(items: List<Cart>): CompletableDeferred<ApiResponse<String>> {
        return super.executeUpdate(db.cartDao().insertItems(items), "Items inserted successfully")
    }

    suspend fun updateItem(item: Cart): CompletableDeferred<ApiResponse<String>> {
        return super.executeUpdate(db.cartDao().updateItem(item), "Item updated successfully")
    }

    suspend fun updateItems(items: List<Cart>): CompletableDeferred<ApiResponse<String>> {
        return super.executeUpdate(db.cartDao().updateItems(items), "Items updated successfully")
    }

    suspend fun deleteItem(item: Cart): CompletableDeferred<ApiResponse<String>> {
        return super.executeUpdate(db.cartDao().deleteItem(item), "Item deleted successfully")
    }
}
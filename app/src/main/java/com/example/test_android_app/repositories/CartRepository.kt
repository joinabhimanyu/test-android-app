package com.example.test_android_app.repositories

import android.content.Context
import com.example.test_android_app.database.CartDatabase
import com.example.test_android_app.entities.Cart
import com.example.test_android_app.models.ApiResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class CartRepository(context: Context) {

    private val db: CartDatabase = CartDatabase.getDatabase(context)

    suspend fun getAllItems(): CompletableDeferred<ApiResponse<List<Cart>>> {
        val response = CompletableDeferred<ApiResponse<List<Cart>>>()
        db.cartDao().getAllItems().catch {
            response.complete(
                ApiResponse(
                    null,
                    isLoading = false,
                    isError = true, error = it.message
                )
            )
        }.collect { items ->
            response.complete(
                ApiResponse(
                    result = items,
                    isLoading = false,
                    isError = false,
                    error = null
                )
            )
        }
        return response
    }

    suspend fun getItemById(id: Int): CompletableDeferred<ApiResponse<Cart>> {
        val response = CompletableDeferred<ApiResponse<Cart>>()
        db.cartDao().getItemById(id).catch {
            response.complete(
                ApiResponse(
                    null,
                    isLoading = false,
                    isError = true, error = it.message
                )
            )
        }.collectLatest { item ->
            response.complete(
                ApiResponse(
                    result = item,
                    isLoading = false,
                    isError = false,
                    error = null
                )
            )
        }
        return response
    }

    suspend fun insertItem(item: Cart): CompletableDeferred<ApiResponse<Cart>> {
        val response = CompletableDeferred<ApiResponse<Cart>>()
        try {
            db.cartDao().insertItem(item).also {
                response.complete(
                    ApiResponse(
                        null,
                        isLoading = false,
                        isError = false,
                        error = null
                    )
                )
            }
        } catch (e: Exception) {
            response.complete(
                ApiResponse(
                    null,
                    isLoading = false,
                    isError = true,
                    error = e.message
                )
            )
        }
        return response
    }
}
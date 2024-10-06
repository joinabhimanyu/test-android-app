package com.example.test_android_app.repositories

import com.example.test_android_app.models.ApiResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

open class BaseRoomRepository {

    suspend fun<T> executeCall(call: Flow<T>): CompletableDeferred<ApiResponse<T>> {
        val response = CompletableDeferred<ApiResponse<T>>()
        call.catch { err: Throwable->
            response.complete(
                ApiResponse(
                    null,
                    isLoading = false,
                    isError = true, error = err.message
                )
            )
        }.collect { items: T ->
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

    suspend fun<T> executeUpdate(call: Unit, message: T): CompletableDeferred<ApiResponse<T>> {
        val response = CompletableDeferred<ApiResponse<T>>()
        try {
            call.also {
                response.complete(
                    ApiResponse(
                        message,
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
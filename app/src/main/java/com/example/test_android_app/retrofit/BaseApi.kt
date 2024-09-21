package com.example.test_android_app.retrofit

import com.example.test_android_app.models.ApiResponse
import com.example.test_android_app.models.Photos
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface BaseApi {
    companion object {
        inline fun <reified T> getInstance(url: String): T
                where T : BaseApi {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(T::class.java)
        }
        
        inline fun <reified T> enqueueResponse(response: Call<T>): CompletableDeferred<ApiResponse<T>> {
            val resp= CompletableDeferred<ApiResponse<T>>()
            response.enqueue(object: Callback<T> {

                override fun onResponse(p0: Call<T>, p1: Response<T>) {
                    if (p1.isSuccessful){
                        val isLoading= false
                        val isError= false
                        val result=p1.body() as T
                        resp.complete(ApiResponse(result = result, isLoading = isLoading, isError = isError, error = null))
                    } else {
                        resp.complete(ApiResponse(result = null, isLoading = false, isError = true, error = "Some error occurred"))
                    }
                }

                override fun onFailure(p0: Call<T>, p1: Throwable) {
                    val isLoading= false
                    val isError= true
                    val error= p1.message!!
                    resp.complete(ApiResponse(result = null, isLoading = isLoading, isError = isError, error = error))
                }
            })
            return resp
        }
    }
}
package com.example.test_android_app.retrofit

import com.example.test_android_app.models.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PhotoApi : BaseApi {

    @Headers(
        "Accept: application/json"
    )

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("photos/{id}")
    fun getPhotoById(@Path("id") id: String): Call<Photos>

    companion object {
        fun getInstance(): PhotoApi {
            return BaseApi.getInstance<PhotoApi>("https://jsonplaceholder.typicode.com/")
        }
        inline fun <reified T> enqueueResponse(response: Call<T>)=BaseApi.enqueueResponse(response)
    }
}
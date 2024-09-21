package com.example.test_android_app.repositories

import com.example.test_android_app.models.ApiResponse
import com.example.test_android_app.models.Photos
import com.example.test_android_app.retrofit.PhotoApi
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call

class PhotoRepository {

    private var api: PhotoApi = PhotoApi.getInstance()

    fun getPhotos(): CompletableDeferred<ApiResponse<List<Photos>>> {
        val response: Call<List<Photos>> = api.getPhotos()
        return PhotoApi.enqueueResponse<List<Photos>>(response)
    }

    fun getPhotoById(id: String): CompletableDeferred<ApiResponse<Photos>> {
        val response: Call<Photos> = api.getPhotoById(id)
        return PhotoApi.enqueueResponse<Photos>(response)
    }

//    suspend fun getPhotosList() = coroutineScope {
//        async { return@async getPhotos().await() }
//    }
}
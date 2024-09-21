package com.example.test_android_app.services

import androidx.lifecycle.MutableLiveData
import com.example.test_android_app.models.Photos
import com.example.test_android_app.repositories.PhotoRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PhotoService(private val repository: PhotoRepository?): BaseService() {

    suspend fun getPhotos(
        photos: MutableLiveData<List<Photos>>

    ) = coroutineScope {

        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository!!.getPhotos().await()
                setState(resp.isLoading, resp.isError, resp.error)
                photos.value = resp.result

            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
                photos.value = null

            }
        }
    }

    suspend fun getPhotoById(
        id: String,
        photos: MutableLiveData<Photos>
    ) = coroutineScope {

        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository!!.getPhotoById(id).await()
                setState(resp.isLoading, resp.isError, resp.error!!)
                photos.value = resp.result

            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message!!)
                photos.value = null

            }
        }
    }
}
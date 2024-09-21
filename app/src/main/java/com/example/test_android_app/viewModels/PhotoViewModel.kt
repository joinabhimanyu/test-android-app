package com.example.test_android_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android_app.models.Photos
import com.example.test_android_app.services.PhotoService
import kotlinx.coroutines.launch

class PhotoViewModel(private val service: PhotoService) : BaseViewModel() {

    private var _photos = MutableLiveData<List<Photos>>(emptyList())
    private var _photo = MutableLiveData<Photos>()

    val photos: LiveData<List<Photos>>
        get() = _photos
    val photo: LiveData<Photos>
        get() = _photo

    init {
        service.initState(_isLoading, _isError, _error)
        viewModelScope.launch {
            service.getPhotos(_photos)
        }
    }

    suspend fun getPhotoById() {
        viewModelScope.launch {
            service.getPhotoById("", _photo)
        }
    }
}
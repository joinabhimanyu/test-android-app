package com.example.test_android_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_android_app.models.Photos

open class BaseViewModel: ViewModel() {
    protected var _isLoading = MutableLiveData(false)
    protected var _isError = MutableLiveData(false)
    protected var _error = MutableLiveData<String?>(null)

    val isLoading: LiveData<Boolean>
        get() = _isLoading
    val isError: LiveData<Boolean>
        get() = _isError
    val error: LiveData<String?>
        get() = _error

}
package com.example.test_android_app.services

import androidx.lifecycle.MutableLiveData

open class BaseService {
    private
    lateinit var isLoading: MutableLiveData<Boolean>

    private
    lateinit var isError: MutableLiveData<Boolean>

    private
    lateinit var error: MutableLiveData<String?>

    fun initState(
        _isLoading: MutableLiveData<Boolean>,
        _isError: MutableLiveData<Boolean>,
        _error: MutableLiveData<String?>,
    ) {
        isLoading = _isLoading
        isError = _isError
        error = _error
    }

    fun setState(_isLoading: Boolean, _isError: Boolean, _error: String?) {
        isLoading.value = _isLoading
        isError.value = _isError
        error.value = _error?.let { "Some error occurred" }
    }
}
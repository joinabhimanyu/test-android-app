package com.example.test_android_app.models

data class ApiResponse<T>(
    val result: T?,
    val isLoading: Boolean,
    val isError: Boolean,
    val error: String?
)

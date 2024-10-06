package com.example.test_android_app.services

import androidx.lifecycle.MutableLiveData
import com.example.test_android_app.entities.Cart
import com.example.test_android_app.repositories.CartRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CartService(private val repository: CartRepository) : BaseService() {

    suspend fun getAllItems(items: MutableLiveData<List<Cart>>) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.getAllItems().await()
                setState(resp.isLoading, resp.isError, resp.error)
                items.value = resp.result
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
                items.value = null
            }
        }
    }

    suspend fun getItemById(id: Int, item: MutableLiveData<Cart>) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.getItemById(id).await()
                setState(resp.isLoading, resp.isError, resp.error)
                item.value = resp.result
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
                item.value = null
            }
        }
    }

    suspend fun insertItem(item: Cart) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.insertItem(item).await()
                setState(resp.isLoading, resp.isError, resp.error)
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
            }
        }
    }

    suspend fun insertItems(items: List<Cart>) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.insertItems(items).await()
                setState(resp.isLoading, resp.isError, resp.error)
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
            }
        }
    }

    suspend fun updateItem(item: Cart) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.updateItem(item).await()
                setState(resp.isLoading, resp.isError, resp.error)
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
            }
        }
    }

    suspend fun updateItems(items: List<Cart>) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.updateItems(items).await()
                setState(resp.isLoading, resp.isError, resp.error)
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
            }
        }
    }

    suspend fun deleteItem(item: Cart) = coroutineScope {
        launch {
            try {
                setState(_isLoading = true, _isError = false, _error = null)
                val resp = repository.deleteItem(item).await()
                setState(resp.isLoading, resp.isError, resp.error)
            } catch (e: Exception) {
                setState(_isLoading = false, _isError = true, _error = e.message)
            }
        }
    }
}
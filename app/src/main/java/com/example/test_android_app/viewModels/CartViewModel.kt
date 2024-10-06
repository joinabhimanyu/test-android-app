package com.example.test_android_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_android_app.entities.Cart
import com.example.test_android_app.services.CartService
import kotlinx.coroutines.launch

class CartViewModel(private val service: CartService) : BaseViewModel() {
    private var _cartItems = MutableLiveData<List<Cart>>(emptyList())
    private var _cartItem=MutableLiveData<Cart>()
    private var _itemId=MutableLiveData<Int>()
    val CartItems: LiveData<List<Cart>>
        get() = _cartItems

    val CarItem: LiveData<Cart>
        get() = _cartItem

    val ItemId: LiveData<Int>
        get() = _itemId
    init {
        service.initState(_isLoading, _isError, _error)
        viewModelScope.launch {
            service.getAllItems(_cartItems)
        }
    }

    suspend fun getItemById() {
        viewModelScope.launch {
            service.getItemById(_itemId.value!!, _cartItem)
        }
    }
    fun addItemToCart(item: Cart) {
        viewModelScope.launch {
            service.insertItem(item)
            service.getAllItems(_cartItems)
        }
    }

    fun addItemsToCart(items: List<Cart>) {
        viewModelScope.launch {
            service.insertItems(items)
            service.getAllItems(_cartItems)
        }
    }

    fun updateItemToCart(item: Cart) {
        viewModelScope.launch {
            service.updateItem(item)
            service.getAllItems(_cartItems)
        }
    }

    fun updateItemsToCart(items: List<Cart>) {
        viewModelScope.launch {
            service.updateItems(items)
            service.getAllItems(_cartItems)
        }
    }

    fun removeItemFromCart(item: Cart) {
        viewModelScope.launch {
            service.deleteItem(item)
            service.getAllItems(_cartItems)
        }
    }

//    fun addItemsToCart(items: List<CartModel>) {
//        viewModelScope.launch {
//            cartItems.value = cartItems.value!!.toMutableList().plus(items)
//        }
//    }
//
//    fun removeItemFromCart(item: CartModel) {
//        viewModelScope.launch {
//            val exists = cartItems.value!!.toMutableList().find { x -> x.id == item.id }
//            if (exists != null) {
//                cartItems.value!!.toMutableList().removeIf { x -> x.id == item.id }
//            }
//        }
//    }
//
//    fun removeItemsFromCart(items: List<CartModel>) {
//        viewModelScope.launch { cartItems.value!!.toMutableList().removeAll(items) }
//    }
}

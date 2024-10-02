package com.example.test_android_app.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.test_android_app.viewModels.CartViewModel
import kotlinx.coroutines.launch

@Composable
fun CartWidget(viewModel: CartViewModel, snackbarHostState: SnackbarHostState) {

    val carItems = viewModel.CartItems.observeAsState(emptyList())
    val scope = rememberCoroutineScope()

    IconButton(onClick = {
        scope.launch {
            snackbarHostState.showSnackbar("shopping cart clicked")
        }
    }) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "Cart"
        )
    }
}
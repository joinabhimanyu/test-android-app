package com.example.test_android_app.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.test_android_app.viewModels.PhotoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosList(
    viewModel: PhotoViewModel,
    snackbarHostState: SnackbarHostState,
    renderActionsComposable: (actions: @Composable() (RowScope.() -> Unit)?) -> Unit,
) {

    val photos = viewModel.photos.observeAsState(emptyList())
    val scope= rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        renderActionsComposable {
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
    }

    LazyColumn {
        items(photos.value.size) { index ->
            val photo = photos.value[index]
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = photo.thumbnailUrl,
                    contentDescription = "${photo.id}",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(10.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp),

                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = photo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
package com.example.test_android_app.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test_android_app.viewModels.BaseViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T> BasePageWrapper(
    title: String?,
    viewModel: T,
    navController: NavHostController?,
    crossinline content: @Composable (viewModel: T) -> Unit,
) where T : BaseViewModel {
    val isLoading = viewModel.isLoading.observeAsState(false)
    val isError = viewModel.isError.observeAsState(false)
    val error = viewModel.error.observeAsState(null)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.background(Color.White),
                drawerShape = RoundedCornerShape(0.dp)
            ) {
                Text(text = "Drawer Header", modifier = Modifier.padding(16.dp))
                Column(modifier = Modifier.fillMaxHeight().absolutePadding(top = 100.dp)) {
                    Route.Screens.mapIndexed { _, route ->
                        if (route.isDetails == false) {
                            NavigationDrawerItem(
                                label = { Text(text = route.title!!) },
                                selected = false,
                                icon = {
                                    Image(
                                        imageVector = route.icon!!,
                                        contentDescription = route.title
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                    navController?.navigate(route.routeName?.name!!)
                                })
                        }
                    }
                }
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar(navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Menu",
                        )
                    }
                }, title = { Text(text = title!!) })
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(20.dp)
                    .fillMaxSize()
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                if (isLoading.value) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(200.dp)
                            .fillMaxHeight()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(200.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterVertically))
                        }
                    }
                }
                if (isError.value) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = "$error.value",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.Red
                            )
                        }
                    }
                }
                content(viewModel)
            }
        }
    }
}
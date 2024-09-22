package com.example.test_android_app.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.test_android_app.viewModels.BaseViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T> BasePageWrapper(
    title: String?,
    viewModel: T,
    hasNavigationIcon: Boolean = true,
    navController: NavHostController?,

    crossinline content: @Composable (
        viewModel: T,
        navController: NavHostController,
        snackbarHostState: SnackbarHostState,
        renderActionsComposable: (actions: @Composable() (RowScope.() -> Unit)?) -> Unit,
    ) -> Unit,

    ) where T : BaseViewModel {

    val isLoading = viewModel.isLoading.observeAsState(false)
    val isError = viewModel.isError.observeAsState(false)
    val error = viewModel.error.observeAsState(null)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    var actionsComposable: @Composable() (RowScope.() -> Unit)? = null
    var actionsComposableReceived by remember { mutableIntStateOf(0) }

    val renderActionsComposable = { actions: @Composable() (RowScope.() -> Unit)? ->
        if (actionsComposable == null && actionsComposableReceived == 0)
            actionsComposable = actions;
        actionsComposableReceived++
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.background(Color.White),
                drawerShape = RoundedCornerShape(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = "https://cpmr-islands.org/wp-content/uploads/sites/4/2019/07/Test-Logo-Small-Black-transparent-1.png",
                            contentDescription = "test logo",
                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .height(400.dp)
                        .absolutePadding(top = 10.dp, left = 10.dp)
                ) {
                    LazyColumn {
                        items(Route.Screens.size) {
                            val route = Route.Screens[it]
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
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp)
                        .padding(start = 20.dp, top = 10.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.primary),
                    elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    Text(text = "Login", color = Color.White)
                }
            }
        }) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                TopAppBar(
                    actions = {
                        if (actionsComposableReceived > 0 && actionsComposable != null) {
                            actionsComposable!!()
                        }
                    },
                    navigationIcon = {
                        if (hasNavigationIcon)
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
                    },
                    title = { Text(text = title!!) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                )
            },
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
                content(viewModel, navController!!, snackbarHostState, renderActionsComposable)
            }
        }
    }
}
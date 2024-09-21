package com.example.test_android_app.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.test_android_app.pages.PhotosList
import com.example.test_android_app.repositories.PhotoRepository
import com.example.test_android_app.services.PhotoService
import com.example.test_android_app.viewModels.PhotoViewModel

data class RouteParams<T>(
    val paramName: String?,
    val paramValue: T,
)

sealed class Route(
    val routeName: ScreenTypes?,
    val isDetails: Boolean?,
    val title: String?,
    val icon: ImageVector?,
    val content: (@Composable (navBackStackEntry: NavBackStackEntry, navController: NavHostController?) -> Unit)?,
) {

    companion object {
        enum class ScreenTypes {
            PhotosList,
            PhotoDetails,
            ProductsList,
            ProductDetails,
            Category,
            Orders,
            Locations,
            Account,
            Settings,
            About,
        }

        val startDestination = PhotoList.routeName?.name
        val Screens = listOf(
            PhotoList,
            PhotoDetails,
            ProductsList,
            ProductDetails,
            Category,
            Orders,
            Locations,
            Account,
            Settings,
            About,
        )

        data object PhotoList :
            Route(
                ScreenTypes.PhotosList,
                title = ScreenTypes.PhotosList?.name,
                icon = Icons.Rounded.ShoppingCart,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Photos List",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        PhotosList(viewModel = viewModel)
                    }
                })

        data object PhotoDetails :
            Route(
                ScreenTypes.PhotoDetails,
                title = ScreenTypes.PhotoDetails?.name,
                icon = null,
                isDetails = true,
                content = { _, _ -> ScreenTypes.PhotoDetails?.name?.let { Text(text = it) } }
            )

        data object ProductsList :
            Route(
                ScreenTypes.ProductsList,
                title = ScreenTypes.ProductsList?.name,
                icon = Icons.Rounded.ExitToApp,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Product List",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.ProductsList?.name?.let { Text(text = it) }
                    }
                }
            )

        data object ProductDetails :
            Route(
                ScreenTypes.ProductDetails,
                title = ScreenTypes.ProductDetails?.name,
                icon = null,
                isDetails = true,
                content = { _, _ -> ScreenTypes.ProductDetails?.name?.let { Text(text = it) } }
            )

        data object Category :
            Route(
                ScreenTypes.Category,
                title = ScreenTypes.Category?.name,
                icon = Icons.Rounded.Notifications,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Category",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.Category?.name?.let { Text(text = it) }
                    }
                }
            )

        data object Orders :
            Route(
                ScreenTypes.Orders,
                title = ScreenTypes.Orders?.name,
                icon = Icons.Rounded.FavoriteBorder,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Orders",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.Orders?.name?.let { Text(text = it) }
                    }
                }
            )

        data object Locations :
            Route(
                ScreenTypes.Locations,
                title = ScreenTypes.Locations?.name,
                icon = Icons.Rounded.LocationOn,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Locations",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.Locations?.name?.let { Text(text = it) }
                    }
                }
            )

        data object Account :
            Route(
                ScreenTypes.Account,
                title = ScreenTypes.Account?.name,
                icon = Icons.Rounded.AccountBox,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Account",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.Account?.name?.let { Text(text = it) }
                    }
                }
            )

        data object Settings :
            Route(
                ScreenTypes.Settings,
                title = ScreenTypes.Settings?.name,
                icon = Icons.Rounded.Settings,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "Settings",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.Settings?.name?.let { Text(text = it) }
                    }
                }
            )

        data object About :
            Route(
                ScreenTypes.About,
                title = ScreenTypes.About?.name,
                icon = Icons.Rounded.Phone,
                isDetails = false,
                content = { _, navController ->
                    BasePageWrapper(
                        title = "About",
                        navController = navController,
                        viewModel = PhotoViewModel(PhotoService(PhotoRepository()))
                    ) { viewModel ->
                        ScreenTypes.About?.name?.let { Text(text = it) }
                    }
                }
            )
    }
}
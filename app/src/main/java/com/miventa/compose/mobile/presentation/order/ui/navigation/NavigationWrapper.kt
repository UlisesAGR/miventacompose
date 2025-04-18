/*
 * NavigationWrapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.ui.view.screen.EditProductsScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.OrderScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.profile.ProfileScreen
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(
    viewModel: OrderViewModel,
    goToLogin: () -> Unit,
) {
    val bottomScreens = remember {
        listOf(
            BottomScreens.Order,
            BottomScreens.EditOrder,
            BottomScreens.Profile,
        )
    }
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.APP_NAME)) })
        },
        bottomBar = {
            NavigationBar {
                bottomScreens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Box {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = screen.iconId),
                                    contentDescription = screen.title,
                                )
                            }
                        },
                        label = {
                            Text(screen.title)
                        },
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomScreens.Order,
            modifier = Modifier.padding(padding),
        ) {
            composable<BottomScreens.Order> {
                OrderScreen(
                    viewModel,
                )
            }
            composable<BottomScreens.EditOrder> {
                EditProductsScreen(
                    viewModel,
                )
            }
            composable<BottomScreens.Profile> {
                ProfileScreen(
                    viewModel,
                    goToLogin = goToLogin,
                )
            }
        }
    }
}

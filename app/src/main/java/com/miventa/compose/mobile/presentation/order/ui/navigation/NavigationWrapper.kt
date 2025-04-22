/*
 * NavigationWrapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miventa.compose.mobile.presentation.order.ui.view.screen.EditProductsScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.OrderScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.ProfileScreen
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiState

@Composable
fun NavigationWrapper(
    navController: NavHostController,
    viewModel: OrderViewModel,
    orderUiState: OrderUiState,
) {
    val navItems = remember {
        listOf(
            BottomScreens.Order,
            BottomScreens.EditOrder,
            BottomScreens.Profile,
        )
    }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, screen ->
                    val selected = selectedItemIndex == index
                    NavigationBarItem(
                        alwaysShowLabel = true,
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                selectedItemIndex = index
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Box {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = screen.iconId),
                                    contentDescription = stringResource(id = screen.title),
                                )
                            }
                        },
                        label = { Text(text = stringResource(id = screen.title)) },
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
                OrderScreen(viewModel)
            }
            composable<BottomScreens.EditOrder> {
                EditProductsScreen(viewModel)
            }
            composable<BottomScreens.Profile> {
                ProfileScreen(
                    viewModel,
                    orderUiState,
                )
            }
        }
    }
}

/*
 * OrderNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miventa.compose.mobile.presentation.order.ui.navigation.EditProductsInteractions
import com.miventa.compose.mobile.presentation.order.ui.navigation.Graph
import com.miventa.compose.mobile.presentation.order.ui.navigation.OrderScreens
import com.miventa.compose.mobile.presentation.order.ui.navigation.ProfileInteractions
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.edit.EditProductsScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.order.OrderScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.profile.ProfileScreen

@Composable
fun OrderNavigationGraph(
    navController: NavHostController,
    padding: PaddingValues,
    navigateToLogin: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.ORDER,
        startDestination = OrderScreens.Order.route,
        modifier = Modifier.padding(padding),
    ) {
        composable(OrderScreens.Order.route) {
            OrderScreen()
        }
        composable(OrderScreens.EditOrder.route) {
            EditProductsScreen(
                editProductsInteractions = EditProductsInteractions(
                    navigateToCreateProduct = {
                        navController.navigate(Graph.CREATE) {
                            launchSingleTop = true
                        }
                    },
                    navigateToUpdateProduct = {
                        navController.navigate(Graph.UPDATE) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(route = OrderScreens.Profile.route) {
            ProfileScreen(
                profileInteractions = ProfileInteractions(
                    navigateToLogin = {
                        navigateToLogin()
                    }
                ),
            )
        }
        createNavGraph(navController)
        updateNavGraph(navController)
    }
}

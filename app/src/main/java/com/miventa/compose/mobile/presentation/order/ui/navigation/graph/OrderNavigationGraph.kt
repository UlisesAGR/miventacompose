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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miventa.compose.mobile.presentation.order.ui.navigation.CreateScreens
import com.miventa.compose.mobile.presentation.order.ui.navigation.EditProductsInteractions
import com.miventa.compose.mobile.presentation.order.ui.navigation.OrderScreens
import com.miventa.compose.mobile.presentation.order.ui.navigation.ProfileInteractions
import com.miventa.compose.mobile.presentation.order.ui.navigation.UpdateScreens
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.edit.EditProductsScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.order.OrderScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.profile.ProfileScreen
import com.miventa.compose.mobile.presentation.order.viewModel.order.OrderViewModel

@Composable
fun OrderNavigationGraph(
    navController: NavHostController,
    padding: PaddingValues,
    navigateToLogin: () -> Unit
) {
    val viewModel: OrderViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = OrderScreens.Order.route,
        modifier = Modifier.padding(padding),
    ) {
        composable(OrderScreens.Order.route) {
            OrderScreen(viewModel)
        }
        composable(OrderScreens.EditOrder.route) {
            EditProductsScreen(
                viewModel,
                editProductsInteractions = EditProductsInteractions(
                    navigateToCreateProduct = {
                        navController.navigate(CreateScreens.CreateGraph.route) {
                            launchSingleTop = true
                        }
                    },
                    navigateToUpdateProduct = { productName ->
                        navController.navigate(UpdateScreens.UpdateProduct.passProductName(productName)) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(route = OrderScreens.Profile.route) {
            ProfileScreen(
                viewModel,
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

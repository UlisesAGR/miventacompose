/*
 * UpdateNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.miventa.compose.mobile.presentation.order.ui.navigation.UpdateInteractions
import com.miventa.compose.mobile.presentation.order.ui.navigation.UpdateScreens
import com.miventa.compose.mobile.presentation.order.ui.view.screen.update.UpdateProductScreen

fun NavGraphBuilder.updateNavGraph(navController: NavHostController) {
    navigation(
        route = UpdateScreens.UpdateGraph.route,
        startDestination = UpdateScreens.UpdateProduct.route,
    ) {
        composable(
            route = UpdateScreens.UpdateProduct.route,
            arguments = listOf(navArgument("productName") { type = NavType.StringType }),
        ) { backStackEntry ->
            val productId = requireNotNull(backStackEntry.arguments?.getString("productName"))
            UpdateProductScreen(
                productId,
                updateInteractions = UpdateInteractions(
                    navigateToOrder = {
                        navController.popBackStack()
                    },
                )
            )
        }
    }
}

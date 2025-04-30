/*
 * UpdateNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.miventa.compose.mobile.presentation.order.ui.navigation.CreateInteractions
import com.miventa.compose.mobile.presentation.order.ui.navigation.Graph
import com.miventa.compose.mobile.presentation.order.ui.navigation.UpdateScreen
import com.miventa.compose.mobile.presentation.order.ui.view.screen.create.CreateProductScreen

fun NavGraphBuilder.updateNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.UPDATE,
        startDestination = UpdateScreen.UpdateProduct.route,
    ) {
        composable(route = UpdateScreen.UpdateProduct.route) {
            CreateProductScreen(
                createInteractions = CreateInteractions(
                    navigateToOrder = {
                        navController.popBackStack()
                    },
                )
            )
        }
    }
}

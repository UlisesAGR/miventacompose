/*
 * OrderContainerScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.container

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.presentation.order.ui.navigation.OrderNavigationBar
import com.miventa.compose.mobile.presentation.order.ui.navigation.graph.OrderNavigationGraph

@Composable
fun OrderContainerScreen(navigateToLogin: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { OrderNavigationBar(navController) },
    ) { padding ->
        OrderNavigationGraph(
            navController = navController,
            padding = padding,
            navigateToLogin = navigateToLogin,
        )
    }
}

/*
 * OrderActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.miventa.compose.mobile.presentation.auth.ui.view.activity.AuthActivity
import com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.container.OrderContainerScreen
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.nextActivityEnd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen {
                OrderContainerScreen(
                    navigateToLogin = {
                        navigateToLogin()
                    },
                )
            }
        }
    }

    private fun navigateToLogin() {
        nextActivityEnd(destination = AuthActivity())
    }
}

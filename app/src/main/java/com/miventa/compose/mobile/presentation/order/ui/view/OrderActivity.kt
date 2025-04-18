/*
 * OrderActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.ui.navigation.NavigationWrapper
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiEvent
import com.miventa.compose.mobile.presentation.start.ui.login.view.LoginActivity
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.nextActivityEnd
import com.miventa.compose.mobile.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : ComponentActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen {
                ContainerScreen()
            }
        }
    }

    @Composable
    private fun ContainerScreen() {
        Screen {
            val characterUiEvent = viewModel.orderUiEvent.collectAsState(
                initial = OrderUiEvent.Loading(isLoading = true),
            ).value

            LaunchedEffect(characterUiEvent) {
                if (characterUiEvent is OrderUiEvent.Error) {
                    showToast(handleError(characterUiEvent.exception))
                }
            }

            NavigationWrapper(
                viewModel,
                goToLogin = {
                    goToLogin()
                },
            )
        }
    }

    private fun goToLogin() {
        nextActivityEnd(
            destination = LoginActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}

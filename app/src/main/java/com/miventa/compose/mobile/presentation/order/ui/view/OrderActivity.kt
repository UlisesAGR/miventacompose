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
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.ui.navigation.NavigationWrapper
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiState
import com.miventa.compose.mobile.presentation.start.ui.login.view.LoginActivity
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.Constants.URL_PLAY_STORE
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.nextActivityEnd
import com.miventa.compose.mobile.util.sharedUrl
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator
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
            val navController = rememberNavController()
            val orderUiState by viewModel.orderUiState.collectAsState(OrderUiState())
            val orderUiEvent by viewModel.orderUiEvent.collectAsState(OrderUiEvent.Initial)

            HandleOrderEvents(orderUiEvent, navController)

            Crossfade(targetState = orderUiState.isLoading) { content ->
                if (content) {
                    ProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .wrapContentSize(Alignment.Center),
                    )
                } else {
                    NavigationWrapper(
                        navController,
                        viewModel,
                        orderUiState,
                    )
                }
            }
        }
    }

    @Composable
    private fun HandleOrderEvents(
        event: OrderUiEvent,
        navController: NavHostController,
    ) {
        LaunchedEffect(event) {
            when (event) {
                is OrderUiEvent.Initial -> {}
                is OrderUiEvent.Error -> showToast(handleError(event.exception))
                is OrderUiEvent.ShowInfoDialog -> {}
                is OrderUiEvent.NavigateToLogin -> goToLogin()
                is OrderUiEvent.ShareUrl -> shareUrl()
            }
        }
    }

    private fun shareUrl() {
        sharedUrl(
            title = getString(R.string.share_app),
            url = URL_PLAY_STORE,
            onError = {
                showToast(getString(R.string.sharing_error))
            },
        )
    }

    private fun goToLogin() {
        nextActivityEnd(
            destination = LoginActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}

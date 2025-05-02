/*
 * SplashActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.splash.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.activity.AuthActivity
import com.miventa.compose.mobile.presentation.order.ui.view.activity.OrderActivity
import com.miventa.compose.mobile.presentation.splash.viewmodel.SplashUiEvent
import com.miventa.compose.mobile.presentation.splash.viewmodel.SplashViewModel
import com.miventa.compose.mobile.util.collect
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.nextActivityEnd
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { true }
        setInitUi()
    }

    private fun setInitUi() {
        setFlows()
    }

    private fun setFlows() {
        collect(viewModel.splashUiEvent) { splashUiEvent ->
            when (splashUiEvent) {
                is SplashUiEvent.Error -> handleError(splashUiEvent.exception)
                is SplashUiEvent.GoToAuth -> goToLogin()
                is SplashUiEvent.GoToOrder -> goToOrder()
            }
        }
    }

    private fun goToLogin() {
        nextActivityEnd(destination = AuthActivity())
    }

    private fun goToOrder() {
        nextActivityEnd(destination = OrderActivity())
    }
}

/*
 * SplashActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.start.ui.login.view.LoginActivity
import com.miventa.compose.mobile.presentation.start.viewmodel.splash.SplashUiEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.splash.SplashViewModel
import com.miventa.compose.mobile.presentation.order.OrderActivity
import com.miventa.compose.mobile.util.collect
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.nextActivityEnd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { true }
        enableEdgeToEdge()
        setInitUi()
    }

    private fun setInitUi() {
        setFlows()
    }

    private fun setFlows() {
        collect(splashViewModel.splashUiEvent) { state ->
            when (state) {
                is SplashUiEvent.Error -> handleError(state.exception)
                is SplashUiEvent.GoToLogin -> goToLogin()
                is SplashUiEvent.GoToOrder -> goToOrder()
            }
        }
    }

    private fun goToLogin() {
        nextActivityEnd(
            destination = LoginActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }

    private fun goToOrder() {
        nextActivityEnd(
            destination = OrderActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}

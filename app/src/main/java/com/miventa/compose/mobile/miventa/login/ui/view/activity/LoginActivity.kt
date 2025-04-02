/*
 * LoginActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.miventa.login.ui.navigation.LoginNavigationWrapper
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.LoginViewModel
import com.miventa.compose.mobile.miventa.order.ui.view.activity.OrderActivity
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.nextActivityEnd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen {
                LoginNavigationWrapper(
                    viewModel = loginViewModel,
                    navigateToOrder = {
                        goToOrder()
                    },
                )
            }
        }
    }

    private fun goToOrder() {
        nextActivityEnd(
            destination = OrderActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}

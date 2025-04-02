/*
 * OrderActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.order.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.miventa.login.ui.view.activity.LoginActivity
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
}

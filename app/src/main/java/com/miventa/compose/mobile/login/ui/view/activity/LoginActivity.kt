package com.miventa.compose.mobile.login.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.login.ui.navigation.NavigationWrapper

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen {
                NavigationWrapper()
            }
        }
    }
}
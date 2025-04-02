/*
 * Intent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import android.app.Activity
import android.app.Activity.OVERRIDE_TRANSITION_OPEN
import android.content.Intent
import android.os.Build

@Suppress("DEPRECATION")
fun Activity.nextActivityEnd(
    destination: Activity,
    animIn: Int = 0,
    animOut: Int = 0,
) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, animIn, animOut)
        } else {
            overridePendingTransition(animIn, animOut)
        }
        finish()
    }
}


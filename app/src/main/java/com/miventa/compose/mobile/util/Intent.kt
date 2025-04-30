/*
 * Intent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.nextActivityEnd(destination: Activity) {
    Intent(this, destination::class.java).apply {
        startActivity(this)
    }
}

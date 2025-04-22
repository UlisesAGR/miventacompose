/*
 * Logs.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import android.util.Log
import com.miventa.compose.mobile.util.Constants.ERROR
import com.miventa.compose.mobile.util.Constants.INFO

fun logInfo(message: String) {
    Log.d(INFO, message)
}

fun log(message: String) {
    Log.d(ERROR, message)
}

/*
 * StringExtensions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

fun String.toIntOrZero(): Int =
    if (this.isEmpty()) {
        0
    } else {
        this.toInt()
    }

fun String.toDoubleOrZero(): Double =
    if (this.isEmpty()) {
        0.0
    } else {
        this.toDouble()
    }

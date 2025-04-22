/*
 * Animation.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import com.miventa.compose.mobile.util.Constants.TIME_ANIMATION

fun enterToUp(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInVertically(initialOffsetY = { -it })

fun enterToUpClosest(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInVertically(initialOffsetY = { -it / 2 })

fun enterToDown(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInVertically(initialOffsetY = { it })

fun enterToDownClosest(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInVertically(initialOffsetY = { it / 2 })

fun enterToLeft(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInHorizontally(initialOffsetX = { -it })

fun enterToLeftClosest(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInHorizontally(initialOffsetX = { -it / 2 })

fun enterToRight(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInHorizontally(initialOffsetX = { it })

fun enterToRightClosest(): EnterTransition =
    fadeIn(animationSpec = tween(TIME_ANIMATION)) + slideInHorizontally(initialOffsetX = { it / 2 })

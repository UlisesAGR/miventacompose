/*
 * SplashUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.splash.viewmodel

sealed class SplashUiEvent {

    internal data class Error(val exception: Throwable?) : SplashUiEvent()
    internal data object GoToLogin : SplashUiEvent()
    internal data object GoToOrder : SplashUiEvent()
}

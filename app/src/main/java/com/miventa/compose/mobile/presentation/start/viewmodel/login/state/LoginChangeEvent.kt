/*
 * LoginChangeEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.login.state

sealed class LoginChangeEvent {
    data class Email(val email: String) : LoginChangeEvent()
    data class Password(val password: String) : LoginChangeEvent()
    data class PasswordVisibility(val passwordHidden: Boolean) : LoginChangeEvent()
    data class PasswordConfirm(val passwordConfirm: String) : LoginChangeEvent()
    data class PasswordConfirmVisibility(val passwordConfirmHidden: Boolean) : LoginChangeEvent()
}

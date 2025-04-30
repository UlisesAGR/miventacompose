/*
 * RegisterChangeEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.register

sealed class RegisterChangeEvent {
    data class Email(val email: String) : RegisterChangeEvent()
    data class Password(val password: String) : RegisterChangeEvent()
    data class PasswordVisibility(val passwordHidden: Boolean) : RegisterChangeEvent()
    data class PasswordConfirm(val passwordConfirm: String) : RegisterChangeEvent()
    data class PasswordConfirmVisibility(val passwordConfirmHidden: Boolean) : RegisterChangeEvent()
}

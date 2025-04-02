/*
 * LoginStatus.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

enum class LoginStatus {
    EMPTY_EMAIL,
    INVALID_EMAIL,
    PASSWORD_EMAIL,
}

enum class RecoverStatus {
    EMPTY_EMAIL,
    INVALID_EMAIL,
}

enum class RegisterStatus {
    EMPTY_EMAIL,
    INVALID_EMAIL,
    EMPTY_PASSWORD,
    PASSWORD_LENGTH,
    CONFIRM_PASSWORD_LENGTH,
    PASSWORD_NOT_SAME,
}

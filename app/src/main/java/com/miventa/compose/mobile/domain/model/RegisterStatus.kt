/*
 * RegisterStatus.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.model

enum class RegisterStatus {
    EMPTY_EMAIL,
    INVALID_EMAIL,
    EMPTY_PASSWORD,
    PASSWORD_LENGTH,
    CONFIRM_PASSWORD_LENGTH,
    PASSWORD_NOT_SAME,
    SUCCESS,
}

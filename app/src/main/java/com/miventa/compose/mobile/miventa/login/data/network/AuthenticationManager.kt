/*
 * AuthenticationManager.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.data.network

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthenticationManager {

    suspend fun verifyCurrentUser(): String
    suspend fun isEmailVerified(): Boolean
    suspend fun verifyEmailIsVerified(): Flow<Boolean>
    suspend fun login(
        email: String,
        password: String,
    ): AuthResult

    suspend fun recoverPassword(email: String): Void
    suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult

    suspend fun sendVerificationEmail()
    suspend fun signOut()
}

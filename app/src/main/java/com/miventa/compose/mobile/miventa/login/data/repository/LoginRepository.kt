/*
 * LoginRepository.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2023. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.data.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

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

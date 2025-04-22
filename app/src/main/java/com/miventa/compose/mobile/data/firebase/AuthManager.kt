/*
 * LoginAuthManager.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthManager {
    suspend fun verifyCurrentUser(): String
    suspend fun isEmailVerified(): Boolean
    suspend fun emailHasBenVerified(): Flow<Boolean>
    suspend fun login(
        email: String,
        password: String,
    ): Task<AuthResult>

    suspend fun recoverPassword(email: String): Task<Void>
    suspend fun registerUser(
        email: String,
        password: String,
    ): Task<AuthResult>

    suspend fun sendVerificationEmail()
    suspend fun signOut()
}

/*
 * LoginAuthenticationManagerImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginAuthenticationManagerImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : LoginAuthenticationManager {

    override suspend fun verifyCurrentUser(): String =
        auth.currentUser?.email ?: ""

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult = auth.createUserWithEmailAndPassword(email, password).await()

    override suspend fun sendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()
    }

    override suspend fun verifyEmailIsVerified(): Flow<Boolean> = flow {
        while (true) {
            reload()
            emit(auth.currentUser?.isEmailVerified ?: false)
        }
    }

    private suspend fun reload() {
        auth.currentUser?.reload()?.await()
    }

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult = auth.signInWithEmailAndPassword(email, password).await()

    override suspend fun isEmailVerified(): Boolean =
        auth.currentUser?.isEmailVerified ?: false

    override suspend fun recoverPassword(email: String): Void =
        auth.sendPasswordResetEmail(email).await()
}

/*
 * LoginAuthManagerImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthManagerImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthManager {

    override suspend fun verifyCurrentUser(): String =
        auth.currentUser?.email ?: ""

    override suspend fun isEmailVerified(): Boolean =
        auth.currentUser?.isEmailVerified ?: false

    override suspend fun emailHasBenVerified(): Flow<Boolean> = flow {
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
    ): Task<AuthResult> = auth.signInWithEmailAndPassword(email, password)

    override suspend fun recoverPassword(email: String): Task<Void> =
        auth.sendPasswordResetEmail(email)

    override suspend fun registerUser(
        email: String,
        password: String,
    ): Task<AuthResult> = auth.createUserWithEmailAndPassword(email, password)

    override suspend fun sendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()
    }
}

/*
 * LoginNetworkDataSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.data.firebase.LoginAuthManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginNetworkDataSourceImpl @Inject constructor(
    private val firebaseAuth: LoginAuthManager,
    private val dispatcher: CoroutineDispatcher,
) : LoginNetworkDataSource {

    override suspend fun verifyCurrentUser(): String = withContext(dispatcher) {
        firebaseAuth.verifyCurrentUser()
    }

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        firebaseAuth.registerUser(email, password).await()
    }

    override suspend fun sendVerificationEmail() = withContext(dispatcher) {
        firebaseAuth.sendVerificationEmail()
    }

    override suspend fun emailHasBenVerified(): Flow<Boolean> = withContext(dispatcher) {
        firebaseAuth.emailHasBenVerified()
    }

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        firebaseAuth.login(email, password).await()
    }

    override suspend fun isEmailVerified(): Boolean = withContext(dispatcher) {
        firebaseAuth.isEmailVerified()
    }

    override suspend fun recoverPassword(email: String): Void =
        withContext(dispatcher) {
            firebaseAuth.recoverPassword(email).await()
        }
}

/*
 * LoginNetworkDataSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.data.datasource

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.miventa.login.data.network.AuthenticationManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginNetworkDataSourceImpl @Inject constructor(
    private val authentication: AuthenticationManager
) : LoginNetworkDataSource {

    override suspend fun verifyCurrentUser(): String =
        authentication.verifyCurrentUser()

    override suspend fun registerUser(email: String, password: String): AuthResult =
        authentication.registerUser(email, password)

    override suspend fun sendVerificationEmail() =
        authentication.sendVerificationEmail()

    override suspend fun verifyEmailIsVerified(): Flow<Boolean> =
        authentication.verifyEmailIsVerified()

    override suspend fun login(email: String, password: String): AuthResult =
        authentication.login(email, password)

    override suspend fun isEmailVerified(): Boolean =
        authentication.isEmailVerified()

    override suspend fun signOut() =
        authentication.signOut()

    override suspend fun recoverPassword(email: String): Void =
        authentication.recoverPassword(email)
}

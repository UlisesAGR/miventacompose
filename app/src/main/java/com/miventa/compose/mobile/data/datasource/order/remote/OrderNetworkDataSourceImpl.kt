/*
 * OrderNetworkDataSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource.order.remote

import com.miventa.compose.mobile.data.firebase.AuthManager
import javax.inject.Inject

class OrderNetworkDataSourceImpl @Inject constructor(
    private val authentication: AuthManager
) : OrderNetworkDataSource {

    override suspend fun verifyCurrentUser(): String =
        authentication.verifyCurrentUser()
}

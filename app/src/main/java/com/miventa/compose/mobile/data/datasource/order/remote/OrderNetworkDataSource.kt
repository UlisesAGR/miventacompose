/*
 * OrderNetworkDataSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource.order.remote

interface OrderNetworkDataSource {
    suspend fun verifyCurrentUser(): String
    suspend fun signOut()
}

/*
 * TotalModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.model.order

data class TotalModel(
    val title: String,
    val total: Double,
) : TicketModel

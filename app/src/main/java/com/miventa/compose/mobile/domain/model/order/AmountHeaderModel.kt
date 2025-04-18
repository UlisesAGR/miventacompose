/*
 * AmountHeaderModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.model.order

data class AmountHeaderModel(
    val title: String,
    val subTitle: String,
    val date: String,
    val change: Double,
) : TicketModel

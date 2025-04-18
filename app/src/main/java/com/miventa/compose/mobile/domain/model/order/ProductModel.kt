/*
 * ProductModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.model.order

data class ProductModel(
    val id: Int,
    val name: String,
    val price: Double,
    var quantity: Int,
    val image: String,
    var userEmail: String,
) : TicketModel

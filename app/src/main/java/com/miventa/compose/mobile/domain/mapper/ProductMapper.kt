/*
 * ProductMapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.mapper

import com.miventa.compose.mobile.data.local.model.ProductEntity
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.util.toDoubleOrZero
import com.miventa.compose.mobile.util.toIntOrZero

fun ProductEntity.toDomain(): ProductModel =
    ProductModel(
        id,
        name,
        price.toString(),
        quantity.toString(),
        image,
        userEmail,
    )

fun ProductModel.toEntity(): ProductEntity =
    ProductEntity(
        id,
        name,
        price.toDoubleOrZero(),
        quantity.toIntOrZero(),
        image,
        userEmail,
    )

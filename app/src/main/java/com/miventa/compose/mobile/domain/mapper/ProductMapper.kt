/*
 * ProductMapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.mapper

import com.miventa.compose.mobile.data.local.model.ProductEntity
import com.miventa.compose.mobile.domain.model.order.ProductModel

fun ProductEntity.toDomain(): ProductModel =
    ProductModel(id, name, price, quantity, image, userEmail)

fun ProductModel.toEntity(): ProductEntity =
    ProductEntity(id, name, price, quantity, image, userEmail)

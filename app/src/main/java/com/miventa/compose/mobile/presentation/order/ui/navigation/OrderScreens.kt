/*
 * OrderScreens.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

import com.miventa.compose.mobile.R

sealed class OrderScreens(
    val route: String,
    val title: Int,
    val iconId: Int,
) {
    data object Order : OrderScreens(
        route = "order",
        title = R.string.order,
        iconId = R.drawable.ic_order,
    )

    data object EditOrder : OrderScreens(
        route = "edit_order",
        title = R.string.edit,
        iconId = R.drawable.ic_edit,
    )

    data object Profile : OrderScreens(
        route = "profile",
        title = R.string.profile,
        iconId = R.drawable.ic_person,
    )
}

sealed class CreateScreens(val route: String) {
    data object CreateGraph : CreateScreens(route = "create_product_graph")
    data object CreateProduct : CreateScreens(route = "create_product")
}

sealed class UpdateScreens(val route: String) {
    data object UpdateGraph : UpdateScreens(route = "update_product_graph")
    data object UpdateProduct : UpdateScreens(route = "update_product/{productName}") {
        fun passProductName(productName: String): String = "update_product/$productName"
    }
}

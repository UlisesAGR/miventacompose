/*
 * OrderScreens.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

import com.miventa.compose.mobile.R

object Graph {
    const val ORDER = "order_graph"
    const val CREATE = "create_graph"
    const val UPDATE = "update_graph"
}

sealed class OrderScreens<T>(
    val route: String,
    val title: Int,
    val iconId: Int,
) {
    data object Order : OrderScreens<Order>(
        route = "order",
        title = R.string.order,
        iconId = R.drawable.ic_order,
    )

    data object EditOrder : OrderScreens<EditOrder>(
        route = "edit_order",
        title = R.string.edit,
        iconId = R.drawable.ic_edit,
    )

    data object Profile : OrderScreens<Profile>(
        route = "profile",
        title = R.string.profile,
        iconId = R.drawable.ic_person,
    )
}

sealed class CreateScreens(val route: String) {
    data object CreateProduct : CreateScreens(route = "create_product")
}

sealed class UpdateScreens(val route: String) {
    data object UpdateProduct : UpdateScreens(route = "update_product")
}

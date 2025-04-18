/*
 * Screens.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

import com.miventa.compose.mobile.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(
    val title: String,
    val iconId: Int,
) {
    @Serializable
    data object Order : BottomScreens<Order>(
        title = "Order",
        iconId = R.drawable.ic_order,
    )

    @Serializable
    data object EditOrder : BottomScreens<EditOrder>(
        title = "Edit",
        iconId = R.drawable.ic_edit,
    )

    @Serializable
    data object Profile : BottomScreens<Profile>(
        title = "Profile",
        iconId = R.drawable.ic_person,
    )
}

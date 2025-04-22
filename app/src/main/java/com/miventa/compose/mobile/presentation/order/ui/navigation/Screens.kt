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
    val title: Int,
    val iconId: Int,
) {
    @Serializable
    data object Order : BottomScreens<Order>(
        title = R.string.order,
        iconId = R.drawable.ic_order,
    )

    @Serializable
    data object EditOrder : BottomScreens<EditOrder>(
        title = R.string.edit,
        iconId = R.drawable.ic_edit,
    )

    @Serializable
    data object Profile : BottomScreens<Profile>(
        title = R.string.profile,
        iconId = R.drawable.ic_person,
    )
}

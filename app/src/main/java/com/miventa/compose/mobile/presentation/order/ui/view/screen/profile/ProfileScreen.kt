/*
 * ProfileScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel

@Composable
fun ProfileScreen(
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier,
    goToLogin: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        ProfileContent(
            goToLogin,
            modifier = Modifier
                .wrapContentSize()
                .padding(dimensionResource(R.dimen.padding)),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        viewModel = hiltViewModel(),
        goToLogin = {},
    )
}

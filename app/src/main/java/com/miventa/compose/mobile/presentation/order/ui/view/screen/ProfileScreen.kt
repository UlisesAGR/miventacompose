/*
 * ProfileScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.viewModel.OrderViewModel
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiState
import com.miventa.compose.mobile.widget.SectionsItem
import com.miventa.compose.mobile.widget.SectionsItemAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: OrderViewModel,
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier,
) = with(orderUiState) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.profile)) })
        },
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
            ) {
                SectionsItem(
                    icon = Icons.Filled.Email,
                    text = currentEmail,
                )
                SectionsItemAction(
                    icon = Icons.Filled.Share,
                    text = stringResource(R.string.share_app),
                    onClick = {
                        viewModel.shareUrl()
                    },
                )
                SectionsItemAction(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    text = stringResource(R.string.logout),
                    onClick = {
                        viewModel.singOut()
                    },
                )
            }
        }
    }
}

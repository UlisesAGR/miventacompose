/*
 * ProfileScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.profile

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.ui.navigation.ProfileInteractions
import com.miventa.compose.mobile.presentation.order.viewModel.order.OrderUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.order.OrderViewModel
import com.miventa.compose.mobile.util.Constants.URL_PLAY_STORE
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.sharedUrl
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.SectionsItem
import com.miventa.compose.mobile.widget.SectionsItemAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profileInteractions: ProfileInteractions) {
    val context = LocalContext.current
    val viewModel: OrderViewModel = hiltViewModel()
    val orderUiState by viewModel.orderUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.orderUiEvent.collect { orderUiEvent ->
            context.handleProfileEvent(orderUiEvent, profileInteractions)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.profile)) })
        },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
            ) {
                SectionsItem(
                    icon = Icons.Filled.Email,
                    text = orderUiState.currentEmail,
                )
                SectionsItemAction(
                    icon = Icons.Filled.Share,
                    text = stringResource(R.string.share_app),
                    onClick = {
                        context.sharedUrl(
                            title = context.getString(R.string.share_app),
                            url = URL_PLAY_STORE,
                            onError = {
                                context.showToast(context.getString(R.string.sharing_error))
                            }
                        )
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

private fun Context.handleProfileEvent(
    event: OrderUiEvent,
    profileInteractions: ProfileInteractions,
) {
    when (event) {
        is OrderUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is OrderUiEvent.NavigateToLogin -> {
            profileInteractions.navigateToLogin()
        }
    }
}

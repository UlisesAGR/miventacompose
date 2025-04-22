/*
 * Shared.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.miventa.compose.mobile.util.Constants.PDF_TYPE
import com.miventa.compose.mobile.util.Constants.TEXT_TYPE

fun Context.sharedPdf(
    title: String,
    uri: Uri?,
    onError: () -> Unit,
) {
    runCatching {
        startActivity(
            Intent.createChooser(
                Intent().apply {
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    type = PDF_TYPE
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, uri)
                },
                title,
            )
        )
    }.onFailure {
        onError()
    }
}

fun Context.sharedUrl(
    title: String,
    url: String,
    onError: () -> Unit,
) {
    runCatching {
        startActivity(
            Intent.createChooser(
                Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = TEXT_TYPE
                    putExtra(Intent.EXTRA_TITLE, title)
                },
                title,
            )
        )
    }.onFailure {
        onError()
    }
}

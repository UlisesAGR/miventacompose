/*
 * SectionsItems.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.miventa.compose.mobile.R

@Composable
fun SectionsItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space)),
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_big)),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
            )
            Text(
                text = text,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
            )
        }
        Divider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun SectionsItemAction(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space)),
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_big)),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
            )
            Text(
                text = text,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
            )
        }
        Divider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionsItemPreview() {
    SectionsItem(
        icon = Icons.Filled.Email,
        text = stringResource(R.string.example),
    )
}

@Preview(showBackground = true)
@Composable
private fun SectionItemPreview() {
    SectionsItemAction(
        icon = Icons.Filled.Email,
        text = stringResource(R.string.example),
        onClick = {},
    )
}

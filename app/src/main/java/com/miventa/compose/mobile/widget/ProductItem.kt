/*
 * ProductItem.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.util.Constants.ANIMATION_DURATION

@Composable
fun ProductItem(
    product: ProductModel,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) = with(product) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onEdit()
            },
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding)),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(ANIMATION_DURATION)
                    .placeholder(R.drawable.il_logo)
                    .error(R.drawable.il_logo)
                    .build(),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_small)),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = name,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = stringResource(
                        id = R.string.price_inter,
                        price,
                    ),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete),
                )
            }
        }
        Divider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        product = ProductModel(
            id = 1,
            name = stringResource(R.string.example),
            price = 10.0,
            quantity = 1,
            image = "",
            userEmail = "",
        ),
        onEdit = {},
        onDelete = {},
    )
}

/*
 * TextField.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.miventa.compose.mobile.R

@Composable
fun NameTextField(
    text: String,
    hint: String,
    imeAction: ImeAction,
    maxLength: Int,
    onTextFieldChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            if (it.length <= maxLength) {
                onTextFieldChanged(it)
            }
        },
        singleLine = true,
        maxLines = 1,
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
        ),
        supportingText = {
            Text(
                text = "${text.length} / $maxLength",
                style = MaterialTheme.typography.labelSmall,
            )
        },
    )
}

@Composable
fun PriceTextField(
    text: String,
    hint: String,
    imeAction: ImeAction,
    onTextFieldChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onTextFieldChanged(it) },
        singleLine = true,
        maxLines = 1,
        label = { Text(hint) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction,
        ),
    )
}

@Composable
fun EmailTextField(
    email: String,
    hint: String,
    imeAction: ImeAction,
    onTextFieldChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        singleLine = true,
        maxLines = 1,
        label = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.email_icon)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
        ),
    )
}

@Composable
fun PasswordTextField(
    password: String,
    passwordHidden: Boolean,
    hint: String,
    imeAction: ImeAction,
    onTextFieldChanged: (String) -> Unit,
    onClickPasswordHidden: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        singleLine = true,
        maxLines = 1,
        label = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Password,
                contentDescription = stringResource(R.string.password_icon)
            )
        },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        trailingIcon = {
            IconButton(onClick = { onClickPasswordHidden() }) {
                Icon(
                    imageVector = if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_big)),
    ) {
        NameTextField(
            text = stringResource(R.string.example),
            hint = stringResource(R.string.example),
            imeAction = ImeAction.Next,
            maxLength = 10,
            onTextFieldChanged = {},
        )
        PriceTextField(
            text = stringResource(R.string.example),
            hint = stringResource(R.string.example),
            imeAction = ImeAction.Next,
            onTextFieldChanged = {},
        )
        EmailTextField(
            email = stringResource(R.string.example),
            hint = stringResource(R.string.example),
            imeAction = ImeAction.Next,
            onTextFieldChanged = {},
        )
        PasswordTextField(
            password = stringResource(R.string.example),
            passwordHidden = true,
            hint = stringResource(R.string.example),
            imeAction = ImeAction.Done,
            onTextFieldChanged = {},
            onClickPasswordHidden = {},
        )
    }
}

package com.miventa.compose.mobile.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.miventa.compose.mobile.R

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

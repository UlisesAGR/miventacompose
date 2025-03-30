package com.miventa.compose.mobile.login.ui.view.screen.recover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField

@Composable
fun RecoverScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
) {

    val emailState by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_big)),
        ) {
            ButtonCircular(
                modifier = Modifier,
                image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                onClick = {
                    navigateToLogin()
                },
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = "Recover password",
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                text = "Here you can recover your password",
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_small)))
            Text(
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall,
                text = "Enter your email",
            )
            EmailTextField(
                email = emailState,
                hint = "Email",
                imeAction = ImeAction.Next,
                onTextFieldChanged = { email ->
                    println(email)
                },
            )
            ButtonPrimary(
                text = "Recover",
                onClick = {
                },
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun RecoverScreenPreview() {
    Screen {
        RecoverScreen()
    }
}

package com.miventa.compose.mobile.login.ui.view.screen.recover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.widget.ButtonPrimary

@Composable
fun ValidateRecoverScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big)),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_big)),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.il_message_sent),
            contentDescription = "Email sent",
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            text = "The email has been sent",
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            text = "The email has been sent to reset your password",
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        ButtonPrimary(
            text = "Accept",
            onClick = {
                navigateToLogin()
            },
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun ValidateRecoverScreenPreview() {
    Screen {
        ValidateRecoverScreen()
    }
}

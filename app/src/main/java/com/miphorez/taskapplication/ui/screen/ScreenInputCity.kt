package com.miphorez.taskapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miphorez.taskapplication.R
import com.miphorez.taskapplication.ui.theme.Purple700

@Composable
fun ScreenInputCity(
    cityName: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onImeAction: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Column {
                EditField(
                    text = cityName,
                    onValueChange = onValueChange,
                    onImeAction = onImeAction
                )
                SendButton {
                    onImeAction()
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.7f))
    }
}

@Composable
private fun EditField(
    text: String,
    onValueChange: (String) -> Unit = {},
    onImeAction: () -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.input_city),
                    style = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Left)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        textStyle = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Left),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}

@Composable
private fun SendButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Purple700,
                    RoundedCornerShape(5.dp)
                )
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.send_title),
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

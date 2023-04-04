package com.android.batya.dreams.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.InputFieldBackground
import com.android.batya.dreams.ui.theme.PlaceholderColor


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "",
    placeholder: String = "",
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it},
        label = {
            Text(
                text = labelId,
                fontSize = 13.sp
            )
       },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 13.sp
            )
        },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 13.sp,
            color = Color.White
        ),
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        keyboardActions =  onAction,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BorderColor,
            unfocusedBorderColor = BorderColor,
            backgroundColor = InputFieldBackground,
            textColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            placeholderColor = PlaceholderColor,
        ),
    )
}

@Preview
@Composable
fun InputFieldPreview() {

    val textFieldValue = rememberSaveable { mutableStateOf("") }
    InputField(
        valueState = textFieldValue,
        placeholder = "пум пурум",
        enabled = true,
        onAction = KeyboardActions { }
    )
}
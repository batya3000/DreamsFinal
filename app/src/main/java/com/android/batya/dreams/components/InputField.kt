package com.android.batya.dreams.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.InputFieldBackgroundColor
import com.android.batya.dreams.ui.theme.PlaceholderColor


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String = "",
    labelId: String = "",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit = {},
    onClick: () -> Unit = {},
) {

    OutlinedTextField(
        modifier = modifier
            .defaultMinSize(minHeight = 51.dp)
            .clickable {
               onClick()
            },
        shape = RoundedCornerShape(7.dp),
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            onValueChanged(it)
        },
        //label = { if (labelId != "") Text(text = labelId) },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 13.sp,
                overflow = TextOverflow.Ellipsis
            )
        },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 13.sp,
            color = Color.White
        ),
        enabled = enabled,
        keyboardActions =  onAction,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BorderColor,
            unfocusedBorderColor = BorderColor,
            disabledBorderColor = BorderColor,
            backgroundColor = InputFieldBackgroundColor,
            textColor = Color.White,
            disabledTextColor = Color.White,
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
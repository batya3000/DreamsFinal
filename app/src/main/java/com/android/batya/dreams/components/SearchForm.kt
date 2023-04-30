package com.android.batya.dreams.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.android.batya.dreams.screens.search.SearchScreenViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    valueState: MutableState<String> = mutableStateOf(""),
    viewModel: SearchScreenViewModel,
    loading: Boolean = false,
    hint: String = "Search",
    onClick: () -> Unit = {},
    onSearch: (String) -> Unit = {},

    ) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        val searchQueryState = rememberSaveable { valueState }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        InputField(
            modifier = modifier,
            valueState = searchQueryState,
            placeholder = placeholder,
            enabled = true,
            onAction = KeyboardActions {
                if(!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}
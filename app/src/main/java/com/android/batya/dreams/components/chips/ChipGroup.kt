package com.android.batya.dreams.components.chips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.batya.dreams.model.Tag

@Composable
fun ChipGroup(
    tags: List<Tag>,
    onChipClicked: (Tag) -> Unit = {}
) {
    Column(modifier = Modifier) {
        LazyRow {
            items(tags) {
                TagChip(
                    tag = it,
                ) { tag ->
                    onChipClicked(tag)
                }
            }
        }
    }
}
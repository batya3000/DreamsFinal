package com.android.batya.dreams.screens.edit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.batya.dreams.R
import com.android.batya.dreams.components.chips.ChipGroup
import com.android.batya.dreams.components.FieldLabel
import com.android.batya.dreams.components.InputField
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.utils.getFormattedDate
import com.android.batya.dreams.utils.getFormattedTime
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun GeneralScreenContent(dream: Dream, viewModel: EditDreamViewModel) {
    val titleValue = remember { mutableStateOf(dream.title) }
    val descriptionValue = remember { mutableStateOf(dream.description) }
    val tagValue = remember { mutableStateOf("") }

    val dateValue = remember { mutableStateOf(getFormattedDate(dream.date)) }
    val timeValue = remember { mutableStateOf(getFormattedTime(dream.time)) }
    val tags = remember { mutableStateOf(dream.tags) }

    val context = LocalContext.current
    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            dream.date = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth).toEpochDay()
            viewModel.updateDream(dream)
            dateValue.value = getFormattedDate(dream.date)
        }, LocalDate.ofEpochDay(dream.date).year, LocalDate.ofEpochDay(dream.date).monthValue - 1, LocalDate.ofEpochDay(dream.date).dayOfMonth
    )

    val timePicker = TimePickerDialog(
        context,
        {_, selectedHour : Int, selectedMinute: Int ->
            dream.time = LocalTime.of(selectedHour, selectedMinute).toNanoOfDay()
            viewModel.updateDream(dream)
            timeValue.value = getFormattedTime(dream.time)
        }, LocalTime.ofNanoOfDay(dream.time).hour, LocalTime.ofNanoOfDay(dream.time).minute, true
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter

    ) {
        Column(
            modifier = Modifier.padding(horizontal = 37.dp),
        ) {

            Row {
                // DATE AND TIME
                Column {
                    FieldLabel(
                        modifier = Modifier
                            .padding(start = 4.dp, bottom = 8.dp),
                        text = stringResource(R.string.edit_dream_date),
                    )
                    InputField(
                        modifier = Modifier
                            .fillMaxWidth(0.65f),
                        valueState = dateValue,
                        isSingleLine = true,
                        enabled = false,
                    ) { // onCliсk
                        datePicker.show()
                    }
                }

                Column(modifier = Modifier.padding(start = 15.dp)) {
                    FieldLabel(
                        modifier = Modifier
                            .padding(start = 4.dp, bottom = 8.dp),
                        text = stringResource(R.string.edit_dream_time),
                    )
                    InputField(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        valueState = timeValue,
                        isSingleLine = true,
                        enabled = false,
                    ) { // onCliсk
                        timePicker.show()
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            FieldLabel(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp),
                text = stringResource(R.string.edit_dream_title),
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = titleValue,
                placeholder = stringResource(R.string.edit_dream_title_placeholder),
                enabled = true,
                isSingleLine = false,
                onValueChanged = { title ->
                    dream.title = title
                    viewModel.updateDream(dream)
                },

            )

            Spacer(modifier = Modifier.height(20.dp))

            FieldLabel(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp),
                text = stringResource(R.string.edit_dream_description),
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .defaultMinSize(minHeight = 170.dp),
                valueState = descriptionValue,
                placeholder = stringResource(R.string.edit_dream_description_placeholder),
                enabled = true,
                isSingleLine = false,
                onAction = KeyboardActions { },
                onValueChanged = { description ->
                    dream.description = description
                    viewModel.updateDream(dream)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ChipGroup(tags = tags.value) { tag ->
                //Log.d("TAG", "tags before delete: ${tags.value}")
                tags.value -= tag
                dream.tags -= tag
                viewModel.updateDream(dream)
                //Log.d("TAG", "tags after delete: ${tags.value}")
            }
            if ( tags.value != emptyList<Tag>()) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            FieldLabel(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp),
                text = stringResource(R.string.edit_dream_add_tag),
            )

            val isTagValid = remember(tagValue.value) {
                tagValue.value.trim().isNotEmpty() && !tags.value.contains(Tag(tagValue.value.trim()))
            }

            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = tagValue,
                placeholder = stringResource(R.string.edit_dream_add_tag_placeholder),
                enabled = true,
                onAction = KeyboardActions {
                    if (!isTagValid) return@KeyboardActions
                    tags.value += Tag(tagValue.value.trim())
                    dream.tags += Tag(tagValue.value.trim())
                    viewModel.updateDream(dream)
                    tagValue.value = ""
                }
            )
        }
    }
}




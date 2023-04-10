package com.android.batya.dreams.screens.edit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.android.batya.dreams.components.ChipGroup
import com.android.batya.dreams.components.FieldLabel
import com.android.batya.dreams.components.InputField
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.repository.DreamRepository
import com.android.batya.dreams.utils.getFormattedDate
import com.android.batya.dreams.utils.getFormattedTime
import com.android.batya.dreams.utils.parseDateFromString
import com.android.batya.dreams.utils.parseTimeFromString
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DreamGeneralScreen(dream: Dream) {

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
            dateValue.value = getFormattedDate(selectedYear, selectedMonth, selectedDayOfMonth)
            dream.date = parseDateFromString(dateValue.value)
        }, dream.date.year, dream.date.monthValue, dream.date.dayOfMonth
    )

    val timePicker = TimePickerDialog(
        context,
        {_, selectedHour : Int, selectedMinute: Int ->
            timeValue.value = getFormattedTime(selectedHour, selectedMinute)
            dream.time = parseTimeFromString(timeValue.value)
        }, dream.time.hour, dream.time.minute, true
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
                        text = "Дата",
                    )
                    InputField(
                        modifier = Modifier
                            .fillMaxWidth(0.65f),
                        valueState = dateValue,
                        placeholder = "05 апреля 2023 г.",
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
                        text = "Время",
                    )
                    InputField(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        valueState = timeValue,
                        placeholder = "11:48",
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
                text = "Название",
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = titleValue,
                placeholder = "Название сна",
                enabled = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            FieldLabel(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp),
                text = "Содержание",
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .defaultMinSize(minHeight = 170.dp),
                valueState = descriptionValue,
                placeholder = "Подробное описание сна",
                enabled = true,
                isSingleLine = false,
                onAction = KeyboardActions { }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ChipGroup(tags = tags.value) { tag ->
                Log.d("TAG", "tags before delete: ${tags.value}")
                tags.value -= tag
                Log.d("TAG", "tags after delete: ${tags.value}")
            }
            if ( tags.value != emptyList<Tag>()) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            FieldLabel(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp),
                text = "Добавить тег",
            )

            val isTagValid = remember(tagValue.value) {
                tagValue.value.trim().isNotEmpty() && !tags.value.contains(Tag(tagValue.value.trim()))
            }

            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = tagValue,
                placeholder = "Назовите новый тег",
                enabled = true,
                onAction = KeyboardActions {
                    if (!isTagValid) return@KeyboardActions
                    tags.value += Tag(tagValue.value.trim())
                    tagValue.value = ""
                }
            )
        }
    }

    dream.title = titleValue.value
    dream.description = descriptionValue.value
    dream.tags = tags.value
        //Log.d("TAG", "new tags: ${tags.value} and ${dream.tags}")

}




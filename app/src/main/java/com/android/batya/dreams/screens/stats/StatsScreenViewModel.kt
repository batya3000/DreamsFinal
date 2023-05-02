package com.android.batya.dreams.screens.stats

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.batya.dreams.data.DataOrException
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Mood
import com.android.batya.dreams.repository.DreamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Integer.max
import javax.inject.Inject


@HiltViewModel
class StatsScreenViewModel @Inject constructor(private val repository: DreamRepository): ViewModel() {
    val data: MutableState<DataOrException<List<Dream>, Boolean, Exception>>
            = mutableStateOf(DataOrException(listOf(), true,Exception("")))

    private val tagsWithCount = mutableMapOf<String, Int>()
    private val moodsWithCount = mutableMapOf<String, Int>()

    init {
        getDreams()
    }

    private fun getDreams() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getDreams()
            if (!data.value.data.isNullOrEmpty()) data.value.loading = false
        }

        Log.d("GET", "getUsers: ${data.value.data?.toList().toString()}")
    }
    
    fun getMostFrequentTag(): String? {
        if (tagsWithCount.isEmpty()) {
            data.value.data!!.forEach { dream ->
                if (dream.tags.isNotEmpty()) {
                    dream.tags.forEach { tag ->
                        if (tag.title !in tagsWithCount) {
                            tagsWithCount[tag.title!!] = 1
                        } else {
                            tagsWithCount[tag.title!!] = tagsWithCount[tag.title]!! + 1
                        }
                    }
                }
            }
        }

        var mostFrequentTag: String? = null
        var mostFrequentTagCounter = 0

        tagsWithCount.forEach { (key, value) ->
            if (value > mostFrequentTagCounter) {
                mostFrequentTagCounter = value
                mostFrequentTag = key
            }
        }
        return mostFrequentTag
    }

    fun getMostFrequentTagCount(): Int? {
        return if (tagsWithCount.isNotEmpty()) {
            tagsWithCount[getMostFrequentTag()]
        } else null
    }

    fun getMostFrequentMood(): String? {
        if (moodsWithCount.isEmpty()) {
            data.value.data!!.forEach { dream ->
                if (dream.mood != Mood.NOT_SELECTED) {
                    if (dream.mood.title !in moodsWithCount) {
                        moodsWithCount[dream.mood.title] = 1
                    } else {
                        moodsWithCount[dream.mood.title] = moodsWithCount[dream.mood.title]!! + 1
                    }
                }
            }
        }

        var mostFrequentMood: String? = null
        var mostFrequentMoodCounter = 0

        moodsWithCount.forEach { (key, value) ->
            if (value > mostFrequentMoodCounter) {
                mostFrequentMoodCounter = value
                mostFrequentMood = key
            }
        }

        return mostFrequentMood
    }

    fun getMostFrequentMoodCount(): Int? {
        return if (moodsWithCount.isNotEmpty()) {
            moodsWithCount[getMostFrequentMood()]
        } else null
    }

    fun getTagsCount(): Int {
        return tagsWithCount.size
    }


    fun getLongestStreak(): Int {
        val dates = data.value.data!!.sortedWith(compareBy(Dream::date)).map { it.date }
        if (dates.isEmpty()) return 0

        var longestStreak = 1
        var currentStreak = 1

        for (i in 1 until dates.size) {
            if (dates[i] == dates[i - 1] + 1) {
                currentStreak += 1
            } else if (dates[i] != dates[i - 1]) {
                longestStreak = max(longestStreak, currentStreak)
                currentStreak = 1
            }
        }

        Log.d("tag", "sortedDreams: $dates")

        return longestStreak
    }

}

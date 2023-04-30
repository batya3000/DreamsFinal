package com.android.batya.dreams.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.batya.dreams.data.DataOrException
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.navigation.DREAM_ID_ARGUMENT_KEY
import com.android.batya.dreams.repository.DreamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DreamDetailsViewModel @Inject constructor(
    private val repository: DreamRepository,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    val dreamOrException: MutableState<DataOrException<Dream, Boolean, Exception>>
            = mutableStateOf(DataOrException(Dream(), true,Exception("")))
    private val dreamId = savedStateHandle.get<String>(DREAM_ID_ARGUMENT_KEY)


    init {
        getDreamById(dreamId!!)
        //Log.d("TAG", "dreamid: $dreamId")
    }

    private fun getDreamById(dreamId: String) {
        viewModelScope.launch {
            dreamOrException.value.loading = true
            dreamOrException.value = repository.getDreamById(dreamId)
            if (dreamOrException.value.data != null) dreamOrException.value.loading = false
        }
    }

    fun deleteDreamById(dreamId: String) = viewModelScope.launch {
        repository.deleteDreamById(dreamId)
    }
}
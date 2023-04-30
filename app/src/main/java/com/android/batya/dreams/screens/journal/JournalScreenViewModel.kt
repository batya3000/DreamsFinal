package com.android.batya.dreams.screens.journal

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.batya.dreams.data.DataOrException
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.User
import com.android.batya.dreams.repository.DreamRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JournalScreenViewModel @Inject constructor(private val repository: DreamRepository): ViewModel() {
    val data: MutableState<DataOrException<List<Dream>, Boolean, Exception>>
        = mutableStateOf(DataOrException(listOf(), true,Exception("")))

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


}
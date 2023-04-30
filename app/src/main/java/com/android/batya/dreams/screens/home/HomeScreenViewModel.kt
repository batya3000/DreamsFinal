package com.android.batya.dreams.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.batya.dreams.model.Dream

import com.android.batya.dreams.repository.DreamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: DreamRepository): ViewModel() {

    fun addDream(dream: Dream) {
        viewModelScope.launch {
            repository.addDream(dream)
            //Log.d("TAG", "addDream: ${dream.id}")
        }
    }
}
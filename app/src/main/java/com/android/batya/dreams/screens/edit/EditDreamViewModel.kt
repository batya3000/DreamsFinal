package com.android.batya.dreams.screens.edit

import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.repository.DreamRepository


class EditDreamViewModel(private val repository: DreamRepository) {

    fun getDream(): Dream {
        return repository.getDreams()[0]
    }
}
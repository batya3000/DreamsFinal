package com.android.batya.dreams.screens.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel


class LoginScreenViewModel : ViewModel() {

    private val auth = Firebase.auth
}
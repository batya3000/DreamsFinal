package com.android.batya.dreams.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.batya.dreams.components.TranslucentButton
import com.android.batya.dreams.navigation.DreamScreens
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(
    navController: NavController,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 50.dp,
                bottom = 40.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TranslucentButton(
            text = "Logout from Google"
        ) {
            Firebase.auth.signOut()
            navController.navigate(DreamScreens.SignIn.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }
}
package com.android.batya.dreams.screens.edit


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.android.batya.dreams.navigation.Graph

@Composable
fun DreamLucidScreen (
    navController: NavHostController,
//    name: String,
//    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
                Toast.makeText(navController.context, "Сон сохранён", Toast.LENGTH_SHORT).show()
            },
            text = "Dream Lucid",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}
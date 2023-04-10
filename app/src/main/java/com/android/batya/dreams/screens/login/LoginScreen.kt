package com.android.batya.dreams.screens.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.batya.dreams.navigation.Graph
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.batya.dreams.components.IconCard
import com.android.batya.dreams.navigation.DreamScreens
import com.android.batya.dreams.ui.theme.CardBackgroundColor


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                // save into repository
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            },
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Blue.copy(alpha = 0.5f)
            ),
            border = BorderStroke(0.dp, Color.Transparent),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Login via Google",
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
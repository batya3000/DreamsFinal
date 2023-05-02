package com.android.batya.dreams.screens.signin

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.batya.dreams.R
import com.android.batya.dreams.components.TranslucentButton
import com.android.batya.dreams.navigation.DreamScreens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun SignInScreen(
    //onSignInClick: () -> Unit,
    navController: NavController,
    //signInViewModel: SignInViewModel,
) {
    //val context = LocalContext.current
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
            Log.d("TAG", "SignInScreen: user: ${user!!.displayName}")
            //Log.d("TAG", "SignInScreen: navigate: ${user!!.displayName}")
            navController.navigate(DreamScreens.Journal.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        },
        onAuthError = {
            user = null
            Log.d("TAG", "SignInScreen: error: ${it.message}")
        }
    )
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignInContent()

        TranslucentButton(
            text = "Sign in with Google",
            iconDrawable = R.drawable.ic_google_logo,
            shapeCorners = 6.dp
        ) {
            val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token)
                    .requestEmail()
                    .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
            googleSignInClient.signOut()
        }
    }
}

@Composable
fun SignInContent() {
    Text(
        text = stringResource(id = R.string.sign_in_welcome),
        fontSize = 26.sp,
        color = Color.White.copy(1f),
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(40.dp))
    Text(
        text = stringResource(id = R.string.sign_in_text),
        fontSize = 16.sp,
        color = Color.White.copy(0.9f),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(40.dp))

}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}
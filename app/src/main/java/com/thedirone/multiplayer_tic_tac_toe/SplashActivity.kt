package com.thedirone.multiplayer_tic_tac_toe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_CANCELED
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_COMPLETED
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_DOWNLOADING
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_DOWNLOAD_PAUSED
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_FAILED
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_INSTALLING
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState.STATE_PENDING
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.thedirone.multiplayer_tic_tac_toe.features.ui.theme.MultiplayerTicTacToeTheme

class SplashActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplayerTicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   SplashScreenPage(this@SplashActivity)
                }
            }
        }
    }

}

class ModuleInstallProgressListener(val installStateUpdate: (Int) -> Unit) : InstallStatusListener {
    override fun onInstallStatusUpdated(update: ModuleInstallStatusUpdate) {
        val installState = update.installState
        installStateUpdate(installState)
    }
}

@Composable
fun SplashScreenPage(context: Context) {
    val alpha = remember {
        Animatable(0f)
    }

    val isLoading = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true){
        alpha.animateTo(1f,
            animationSpec = tween(1500)
        )
        val moduleInstallClient = ModuleInstall.getClient(context)
        val optionalModuleApi = GmsBarcodeScanning.getClient(context)
        val listener = ModuleInstallProgressListener { installState->
            when (installState) {
                STATE_COMPLETED -> {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                STATE_FAILED, STATE_CANCELED -> {
                    Toast.makeText(context,"Please restart your app and check your internet connection!", Toast.LENGTH_LONG).show()
                    isLoading.value = false
                }
                STATE_PENDING, STATE_INSTALLING, STATE_DOWNLOADING, STATE_DOWNLOAD_PAUSED -> {
                    Log.d("isDownloading", installState.toString())
                    isLoading.value = true
                }
            }
        }

        val moduleInstallRequest =
            ModuleInstallRequest.newBuilder()
                .addApi(optionalModuleApi)
                .setListener(listener)
                .build()

        moduleInstallClient
            .installModules(moduleInstallRequest)
            .addOnSuccessListener {
                if (it.areModulesAlreadyInstalled()) {
                    // Modules are already installed when the request is sent.
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
            .addOnFailureListener {
                // Handle failure…
                Log.d("isDownloading", it.message.toString())
                Toast.makeText(context,"Something went wrong!", Toast.LENGTH_SHORT).show()
            }

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Splash Screen Logo",
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .alpha(alpha.value)
        )
    }

    if(isLoading.value) Box(
        contentAlignment = Alignment.BottomCenter,
    ) {
        CircularProgressIndicator()
    }
}


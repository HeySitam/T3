package com.thedirone.multiplayer_tic_tac_toe

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.thedirone.multiplayer_tic_tac_toe.core.routes.Route
import com.thedirone.multiplayer_tic_tac_toe.features.ui.pages.ClientPageScreen
import com.thedirone.multiplayer_tic_tac_toe.features.ui.pages.ClientQrScannerPage
import com.thedirone.multiplayer_tic_tac_toe.features.ui.pages.HomePageScreen
import com.thedirone.multiplayer_tic_tac_toe.features.ui.pages.ServerPageScreen
import com.thedirone.multiplayer_tic_tac_toe.features.ui.pages.ServerQrShowPage
import com.thedirone.multiplayer_tic_tac_toe.features.ui.theme.MultiplayerTicTacToeTheme
import com.thedirone.multiplayer_tic_tac_toe.features.viewmodels.ClientViewModel
import com.thedirone.multiplayer_tic_tac_toe.features.viewmodels.ServerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moduleInstallClient = ModuleInstall.getClient(this)
        val optionalModuleApi = GmsBarcodeScanning.getClient(this)
        val moduleInstallRequest =
            ModuleInstallRequest.newBuilder()
                .addApi(optionalModuleApi).build()

        moduleInstallClient
            .installModules(moduleInstallRequest)
            .addOnSuccessListener {
                if (it.areModulesAlreadyInstalled()) {
                    // Modules are already installed when the request is sent.
                }
            }
            .addOnFailureListener {
                // Handle failure…
            }

        setContent {
            MultiplayerTicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(ipAddr = getIp(this),this)
                }
            }
        }
    }

    private fun getIp(context: MainActivity): String {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        return ipAddress
    }
}

@Composable
fun MyApp(ipAddr:String, context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.homePageRoute){
        composable(route = Route.homePageRoute){
            HomePageScreen(
                onHostBtnClick = {
                    navController.navigate(Route.serverPageRoute)
                },
                onJoinBtnClick = {
                    navController.navigate(Route.clientPageRoute)
                }
            )
        }
        composable(route = Route.clientPageRoute){
            ClientPageScreen(navController, context = context)
        }
        composable(route = Route.serverPageRoute){
            ServerPageScreen(navController, ipAddr = ipAddr)
        }
    }
}

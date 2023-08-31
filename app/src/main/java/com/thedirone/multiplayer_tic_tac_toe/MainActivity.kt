package com.thedirone.multiplayer_tic_tac_toe

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thedirone.multiplayer_tic_tac_toe.core.routes.Route
import com.thedirone.multiplayer_tic_tac_toe.ui.pages.ClientPageScreen
import com.thedirone.multiplayer_tic_tac_toe.ui.pages.HomePageScreen
import com.thedirone.multiplayer_tic_tac_toe.ui.pages.ServerPageScreen
import com.thedirone.multiplayer_tic_tac_toe.ui.theme.MultiplayerTicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplayerTicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }

    private fun getIp(context: MainActivity): String {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        return ipAddress;
    }
}

//fun getIP(context: Context): String {
//
//}

@Composable
fun MyApp() {
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
            ClientPageScreen()
        }
        composable(route = Route.serverPageRoute){
            ServerPageScreen()
        }
    }
 // HomePageScreen()
}

//@Preview(showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    MultiplayerTicTacToeTheme {
//        Greeting("Android")
//    }
//}
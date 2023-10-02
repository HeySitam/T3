package com.thedirone.multiplayer_tic_tac_toe.features.ui.pages

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.thedirone.multiplayer_tic_tac_toe.core.routes.Route
import com.thedirone.multiplayer_tic_tac_toe.features.viewmodels.ClientViewModel

@Composable
fun ClientQrScannerPage(clientVM: ClientViewModel, navController: NavController, context: Context) {
   // val clientViewModel: ClientViewModel = viewModel()
    val isConnectedWithServer = clientVM.isConnectedWithServer.observeAsState()
    if(isConnectedWithServer.value == true){
        navController.navigate(Route.clientPageRoute)
    }
    remember {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .enableAutoZoom()
            .build()
        val scanner = GmsBarcodeScanning.getClient(context,options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Task completed successfully
                clientVM.connectToServer(ipAddr = barcode.rawValue.toString())
                Toast.makeText(context,"QR code is ${barcode.rawValue.toString()}", Toast.LENGTH_SHORT).show()

            }
            .addOnCanceledListener {
                // Task canceled
                Log.d("chkQR", "task canceled!")
                navController.navigateUp()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Log.d("chkQR", e.message.toString())
            }
    }
}
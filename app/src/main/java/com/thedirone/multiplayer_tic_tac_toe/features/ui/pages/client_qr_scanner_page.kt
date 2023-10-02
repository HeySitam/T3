package com.thedirone.multiplayer_tic_tac_toe.features.ui.pages

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun ClientQrScannerPage(context: Context, onSuccess: (String) -> Unit, onCancel: () -> Unit) {
    remember {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC
            )
            .enableAutoZoom()
            .build()
        val scanner = GmsBarcodeScanning.getClient(context, options)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                onSuccess(barcode.rawValue.toString())
            }
            .addOnCanceledListener {
                // Task canceled
                Log.d("chkQR", "task canceled!")
                onCancel()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Log.d("chkQR", e.message.toString())
            }
    }
}
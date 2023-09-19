package com.thedirone.multiplayer_tic_tac_toe.features.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ClientViewModel : ViewModel() {
    private val _receivedDataFromServer = MutableLiveData<Int>()
    val receivedDataFromServer: LiveData<Int> = _receivedDataFromServer

    private val _clientStatus = MutableLiveData<String>()
    val clientStatus: LiveData<String> = _clientStatus

    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null
//    private var dataInputStream: ObjectInputStream? = null
//    private var dataOutputStream: ObjectOutputStream? = null

    fun connectToServer(ipAddr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                socket = Socket(ipAddr, 2345)
            } catch (e: IOException) {
                Log.d("ClientTesting", "failed to start client socket")
                withContext(Dispatchers.Main){
                    _clientStatus.value = "failed to start client socket"
                }
                e.printStackTrace()
            }

            Log.d("ClientTesting", "creating data streams...")
            withContext(Dispatchers.Main){
                _clientStatus.value = "creating data streams..."
            }
            try {
                dataInputStream = DataInputStream(BufferedInputStream(socket!!.getInputStream()))
                dataOutputStream =
                    DataOutputStream(BufferedOutputStream(socket!!.getOutputStream()))
//                dataInputStream = ObjectInputStream(socket!!.getInputStream())
//                dataOutputStream =
//                    ObjectOutputStream(socket!!.getOutputStream())
//                dataOutputStream!!.flush()
            } catch (e: IOException) {
                Log.d("ClientTesting", "failed to create streams")
                withContext(Dispatchers.Main){
                    _clientStatus.value = "failed to create streams"
                }
                e.printStackTrace()
            }
            withContext(Dispatchers.Main){
                _clientStatus.value = "Connected!"
            }

            try {
                while(true) {
                      val pos = dataInputStream!!.readInt()
                      val data = dataInputStream!!.readInt()
                    Log.d("ClientReceived", "Position is ${pos.toString()}")
                    Log.d("ClientReceived", "data is ${data.toString()}")
//                    Log.d("ClientTesting", "byte received: $test")
//                    withContext(Dispatchers.Main){
//                        _receivedDataFromServer.value = test
//                    }
               }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun sendData(dataToSend: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ClientTesting", "sending test data...")
            try {
                dataOutputStream!!.writeInt(dataToSend)
                dataOutputStream!!.flush()
            } catch (e: IOException) {
                Log.d("ClientTesting", "failed to send")
                e.printStackTrace()
            }
        }
    }

    fun receiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ClientTesting", "reading data from server...")
            try {
                val data = dataInputStream!!.readInt()
                Log.d("ClientTesting", "$data received from server")
            } catch (e: IOException) {
                Log.d("ClientTesting", "failed to send")
                e.printStackTrace()
            }
        }
    }
}
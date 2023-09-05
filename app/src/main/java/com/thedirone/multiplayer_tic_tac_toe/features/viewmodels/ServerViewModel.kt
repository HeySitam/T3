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
import java.net.ServerSocket
import java.net.Socket

class ServerViewModel : ViewModel() {
    private val _receivedDataFromClient = MutableLiveData<Int>()
    val receivedDataFromClient: LiveData<Int> = _receivedDataFromClient

    private val _serverStatus = MutableLiveData<String>()
    val serverStatus: LiveData<String> = _serverStatus

    private var serverSocket: ServerSocket? = null
    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    fun startServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                serverSocket = ServerSocket(5000)
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to start server socket")
                withContext(Dispatchers.Main){
                    _serverStatus.value = "failed to start server socket"
                }
                e.printStackTrace()
            }

            // wait for a connection
            Log.d("ServerTesting", "waiting for connections...")
            withContext(Dispatchers.Main){
                _serverStatus.value = "waiting for connections..."
            }
            try {
                socket = serverSocket!!.accept()
            } catch (e: IOException) {
                withContext(Dispatchers.Main){
                    _serverStatus.value = "failed to accept"
                }
                Log.d("ServerTesting", "failed to accept")
                e.printStackTrace()
            }
            Log.d("ServerTesting", "client connected")
            withContext(Dispatchers.Main){
                _serverStatus.value = "client connected"
            }

                    // create input and output streams
            try {
                dataInputStream = DataInputStream(BufferedInputStream(socket!!.getInputStream()))
                dataOutputStream =
                    DataOutputStream(BufferedOutputStream(socket!!.getOutputStream()))
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to create streams")
                withContext(Dispatchers.Main){
                    _serverStatus.value = "failed to create streams"
                }
                e.printStackTrace()
            }

            try {
                while(true) {
                    val test = dataInputStream!!.readInt()
                    Log.d("ServerTesting", "byte received: $test")
                    withContext(Dispatchers.Main){
                        _receivedDataFromClient.value = test
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun sendData(dataToSend: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataOutputStream!!.writeInt(dataToSend)
                dataOutputStream!!.flush()
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to send: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun receiveData() {
       // viewModelScope.launch(Dispatchers.IO) {
            try {
                while(true) {
                    val test = dataInputStream!!.readUTF()
                    Log.d("ServerTesting", "byte received: $test")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
       // }
    }

    fun closeServer() {
        viewModelScope.launch {

        }
    }
}
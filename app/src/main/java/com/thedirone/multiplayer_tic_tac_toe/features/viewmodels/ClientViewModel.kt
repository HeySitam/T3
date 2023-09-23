package com.thedirone.multiplayer_tic_tac_toe.features.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedirone.multiplayer_tic_tac_toe.core.utils.returnCopiedIntArr
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

// For now Client is for O
// X => 1
// O => 2
class ClientViewModel : ViewModel() {
    private val _receivedDataFromServer = MutableLiveData<Int>()
    val receivedDataFromServer: LiveData<Int> = _receivedDataFromServer

    private val _clientStatus = MutableLiveData<String>()
    val clientStatus: LiveData<String> = _clientStatus

    private val _gameArrayInfo = MutableLiveData<IntArray>()
    val gameArrayInfo: LiveData<IntArray> = _gameArrayInfo

    private val gameArr = IntArray(9)

    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

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
                    withContext(Dispatchers.Main) {
                        if(gameArr[pos] == 0){
                             gameArr[pos] = data
                            _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                        }
                    }
                    Log.d("ClientReceived", "game array is ${gameArr.toList()}")
               }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun sendDataWithPositionToServer(pos:Int, data:Int = 2) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    if(gameArr[pos] == 0){
                         gameArr[pos] = data
                        _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                    }
                }
                dataOutputStream!!.writeInt(pos)
                dataOutputStream!!.writeInt(data)
                dataOutputStream!!.flush()
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to send: ${e.message}")
                e.printStackTrace()
            }
        }
    }

}
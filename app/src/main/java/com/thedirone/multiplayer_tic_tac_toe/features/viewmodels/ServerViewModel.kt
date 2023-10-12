package com.thedirone.multiplayer_tic_tac_toe.features.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedirone.multiplayer_tic_tac_toe.core.utils.isMatchDraw
import com.thedirone.multiplayer_tic_tac_toe.core.utils.isWonGame
import com.thedirone.multiplayer_tic_tac_toe.core.utils.returnCopiedIntArr
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

// For now Server is for X
// X => 1
// O => 2
class ServerViewModel : ViewModel() {

    private val _serverStatus = MutableLiveData<String>()
    val serverStatus: LiveData<String> = _serverStatus

    private val _gameArrayInfo = MutableLiveData<IntArray>()
    val gameArrayInfo: LiveData<IntArray> = _gameArrayInfo

    private val _isOpponentWon = MutableLiveData<Boolean>(false)
    val isOpponentWon: LiveData<Boolean> = _isOpponentWon

    private val _amIWon = MutableLiveData<Boolean>(false)
    val amIWon: LiveData<Boolean> = _amIWon

    private val _isMatchDraw = MutableLiveData<Boolean>(false)
    val isMatchDraw: LiveData<Boolean> = _isMatchDraw

    private val _isConnectedWithClinet = MutableLiveData<Boolean>(false)
    val isConnectedWithClinet: LiveData<Boolean> = _isConnectedWithClinet

    // Server is always Player 1
      var isServerTurn:Boolean = false

    private var gameArr = IntArray(9)

    private var serverSocket: ServerSocket? = null
    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null
    fun startServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                serverSocket = ServerSocket(3000)
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
                serverSocket?.let {
                    socket = it.accept()
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main){
                    _serverStatus.value = "failed to accept"
                }
                Log.d("ServerTesting", "failed to accept")
                e.printStackTrace()
            }
            Log.d("ServerTesting", "client connected")
            withContext(Dispatchers.Main){
                 _isConnectedWithClinet.value = true
                _serverStatus.value = "client connected"
                 isServerTurn = true
                _serverStatus.value = "Your Turn!"
            }
                    // create input and output streams
            try {
                socket?.let {
                    dataInputStream = DataInputStream(BufferedInputStream(it.getInputStream()))
                    dataOutputStream =
                        DataOutputStream(BufferedOutputStream(it.getOutputStream()))
                }
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to create streams")
                withContext(Dispatchers.Main){
                    _serverStatus.value = "failed to create streams"
                }
                e.printStackTrace()
            }

            try {
                while(true) {
                    dataInputStream?.let {
                        val opponentWinningStatus = it.readBoolean()
                        val pos = it.readInt()
                        val data = it.readInt()
                        withContext(Dispatchers.Main) {
                            if(gameArr[pos] == 0){
                                gameArr[pos] = data
                                _isMatchDraw.value = isMatchDraw(_amIWon.value!!, opponentWinningStatus, gameArr)
                                _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                                _isOpponentWon.value = opponentWinningStatus
                                isServerTurn = true
                                if(opponentWinningStatus){
                                    _serverStatus.value = "Your Loose!"
                                } else {
                                    _serverStatus.value = "Your Turn!"
                                }
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun sendDataWithPositionToClient(pos:Int, data:Int = 1) {
        var isServerWon = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    if(gameArr[pos] == 0){
                         gameArr[pos] = data
                        _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                         isServerTurn = false
                        // Here player 1 means Server
                        isServerWon = isWonGame(player = 1, gameArr)
                        _isMatchDraw.value = isMatchDraw(isServerWon, _isOpponentWon.value!!, gameArr)
                        if(isServerWon){
                            _serverStatus.value = "You Won!"
                            _amIWon.value = true
                        } else {
                            _serverStatus.value = "Opponent's Turn!"
                        }
                    }
                }
                dataOutputStream?.let {
                    it.writeBoolean(isServerWon)
                    it.writeInt(pos)
                    it.writeInt(data)
                    it.flush()
                }
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to send: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    fun closeServer() {
        viewModelScope.launch(Dispatchers.IO) {
            serverSocket?.close()
        }
    }

    fun resetGame() {
        gameArr = IntArray(9)
        _gameArrayInfo.value = returnCopiedIntArr(gameArr)
        _isOpponentWon.value = false
        _amIWon.value = false
        _isMatchDraw.value = false
        isServerTurn = true
        _serverStatus.value = "Your Turn!"
    }
}
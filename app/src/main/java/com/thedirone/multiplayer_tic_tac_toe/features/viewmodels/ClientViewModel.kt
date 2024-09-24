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
import java.net.Socket

// For now Client is for O
// X => 1
// O => 2
class ClientViewModel : ViewModel() {

    private val _clientStatus = MutableLiveData<String>()
    val clientStatus: LiveData<String> = _clientStatus

    private val _gameArrayInfo = MutableLiveData<IntArray>()
    val gameArrayInfo: LiveData<IntArray> = _gameArrayInfo

    private val _isOpponentWon = MutableLiveData<Boolean>()
    val isOpponentWon: LiveData<Boolean> = _isOpponentWon

    private val _amIWon = MutableLiveData<Boolean>(false)
    val amIWon: LiveData<Boolean> = _amIWon

    private val _isMatchDraw = MutableLiveData<Boolean>(false)
    val isMatchDraw: LiveData<Boolean> = _isMatchDraw

    private val _isConnectedWithServer = MutableLiveData<Boolean>(false)
    val isConnectedWithServer: LiveData<Boolean> = _isConnectedWithServer

    // Client is always Player 2
    var isClientTurn: Boolean = false

    private var hasGameResetRequestReceived = false

    private var gameArr = IntArray(9)

    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    fun connectToServer(ipAddr: String) {
        Log.d("chkIp", ipAddr)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                socket = Socket(ipAddr,3000)
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    _clientStatus.value = "failed to start client socket"
                }
                e.printStackTrace()
            }
            withContext(Dispatchers.Main) {
                _clientStatus.value = "creating data streams..."
            }
            try {
                socket?.let {
                    dataInputStream = DataInputStream(BufferedInputStream(it.getInputStream()))
                    dataOutputStream =
                        DataOutputStream(BufferedOutputStream(it.getOutputStream()))
                }
            } catch (e: IOException) {
                Log.d("ClientTesting", "failed to create streams")
                withContext(Dispatchers.Main) {
                    _clientStatus.value = "failed to create streams"
                }
                e.printStackTrace()
            }
            withContext(Dispatchers.Main) {
                _isConnectedWithServer.value = true
                _clientStatus.value = "Connected with Server!"
                _clientStatus.value = "Opponent's Turn!"
            }


            try {
                while (true) {
                    dataInputStream?.let {
                        hasGameResetRequestReceived = it.readBoolean()

                        if(hasGameResetRequestReceived){
                            resetGame()
                        } else {
                            val pos = it.readInt()
                            val data = it.readInt()
                            val opponentWinningStatus = it.readBoolean()
                            withContext(Dispatchers.Main) {
                                if (gameArr[pos] == 0) {
                                    gameArr[pos] = data
                                    _isMatchDraw.value = isMatchDraw(_amIWon.value!!, opponentWinningStatus, gameArr)
                                    _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                                    _isOpponentWon.value = opponentWinningStatus
                                    isClientTurn = true
                                    if(opponentWinningStatus){
                                        _clientStatus.value = "Your Loose!"
                                    } else {
                                        _clientStatus.value = "Your Turn!"
                                    }

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

    fun sendDataWithPositionToServer(pos: Int, data: Int = 2) {
        var isClientWon = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    if (gameArr[pos] == 0) {
                        gameArr[pos] = data
                        _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                        isClientTurn = false
                        // Here player 2 means Client
                        isClientWon = isWonGame(player = 2, gameArr)
                        _isMatchDraw.value = isMatchDraw(isClientWon, _isOpponentWon.value!!, gameArr)
                        if(isClientWon){
                            _amIWon.value = true
                            _clientStatus.value = "You Won!"
                        } else {
                            _clientStatus.value = "Opponent's Turn!"
                        }
                    }
                }
                dataOutputStream?.let {
                    // here sending gameResetRequest as false at first
                    it.writeBoolean(false)
                    it.writeInt(pos)
                    it.writeInt(data)
                    it.writeBoolean(isClientWon)
                    it.flush()
                }
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to send: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun sendResetGameRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataOutputStream?.let {
                    it.writeBoolean(true)
                    it.flush()
                }
            } catch (e: IOException) {
                Log.d("ServerTesting", "failed to send: ${e.message}")
                e.printStackTrace()
            }
        }
        resetGame()
    }

    fun closeClient() {
        viewModelScope.launch(Dispatchers.IO) {
            socket?.close()
        }
    }

    private fun resetGame() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                gameArr = IntArray(9)
                _gameArrayInfo.value = returnCopiedIntArr(gameArr)
                _isOpponentWon.value = false
                _amIWon.value = false
                _isMatchDraw.value = false
                isClientTurn = false
                _clientStatus.value = "Opponent's Turn!"
                hasGameResetRequestReceived = false
            }
        }
    }
}
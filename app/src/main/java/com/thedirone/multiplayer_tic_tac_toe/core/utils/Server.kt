package com.thedirone.multiplayer_tic_tac_toe.core.utils

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket


class Server : Runnable {
    private val thread: Thread = Thread(this)
    private var serverSocket: ServerSocket? = null
    private var socket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    init {
        thread.priority = Thread.NORM_PRIORITY
        thread.start()
    }

    override fun run() {
        // create a server socket
        try {
            serverSocket = ServerSocket(5000)
        } catch (e: IOException) {
            Log.d("ServerTesting", "failed to start server socket")
            e.printStackTrace()
        }

        // wait for a connection
        Log.d("ServerTesting", "waiting for connections...")
        try {
            socket = serverSocket!!.accept()
        } catch (e: IOException) {
            Log.d("ServerTesting", "failed to accept")
            e.printStackTrace()
        }
        Log.d("ServerTesting", "client connected")

        // create input and output streams
        try {
            dataInputStream = DataInputStream(BufferedInputStream(socket!!.getInputStream()))
            dataOutputStream = DataOutputStream(BufferedOutputStream(socket!!.getOutputStream()))
        } catch (e: IOException) {
            Log.d("ServerTesting", "failed to create streams")
            e.printStackTrace()
        }

        // send some test data
        try {
            dataOutputStream!!.writeInt(123)
            dataOutputStream!!.flush()
        } catch (e: IOException) {
            Log.d("ServerTesting", "failed to send")
            e.printStackTrace()
        }

        // placeholder recv loop
        while (true) {
            try {
                val test = dataInputStream!!.readInt()
                Log.d("ServerTesting", "byte received: $test")
                if (test == -1) break
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
        }
        Log.d("ServerTesting", "server thread stopped")
    }
}
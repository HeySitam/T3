package com.thedirone.multiplayer_tic_tac_toe.core.utils

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class Client(val ipAddr:String) : Runnable {
    private val thread: Thread = Thread(this)
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
            socket = Socket(ipAddr,5000)
        } catch (e: IOException) {
            Log.d("ClientTesting", "failed to start client socket")
            e.printStackTrace()
        }

//        // wait for a connection
//        println("waiting for connections...")
//        try {
//            socket = serverSocket!!.accept()
//        } catch (e: IOException) {
//            println("failed to accept")
//            e.printStackTrace()
//        }
//        println("client connected")

        // create input and output streams
        Log.d("ClientTesting", "creating data streams...")
        try {
            dataInputStream = DataInputStream(BufferedInputStream(socket!!.getInputStream()))
            dataOutputStream = DataOutputStream(BufferedOutputStream(socket!!.getOutputStream()))
        } catch (e: IOException) {
            Log.d("ClientTesting", "failed to create streams")
            e.printStackTrace()
        }

        // reading data from server
        Log.d("ClientTesting", "reading data from server...")
        try {
            val data = dataInputStream!!.readInt()
            Log.d("ClientTesting", "$data received from server")
        } catch (e: IOException) {
            Log.d("ClientTesting", "failed to send")
            e.printStackTrace()
        }

        // send some test data
        Log.d("ClientTesting", "sending test data...")
        try {
            dataOutputStream!!.writeInt(6789)
            dataOutputStream!!.flush()
        } catch (e: IOException) {
            Log.d("ClientTesting", "failed to send")
            e.printStackTrace()
        }

        // placeholder recv loop
//        while (true) {
//            try {
//                val test = dataInputStream!!.readByte()
//                println("byte received: $test")
//                if (test.toInt() == 42) break
//            } catch (e: IOException) {
//                e.printStackTrace()
//                break
//            }
//        }
        println("server thread stopped")
    }
}
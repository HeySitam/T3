package com.thedirone.multiplayer_tic_tac_toe.core.utils

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
            socket = Socket(ipAddr,12345)
        } catch (e: IOException) {
            println("failed to start client socket")
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
        try {
            dataInputStream = DataInputStream(BufferedInputStream(socket!!.getInputStream()))
            dataOutputStream = DataOutputStream(BufferedOutputStream(socket!!.getOutputStream()))
        } catch (e: IOException) {
            println("failed to create streams")
            e.printStackTrace()
        }

        // send some test data
        try {
            dataOutputStream!!.writeInt(6789)
            dataOutputStream!!.flush()
        } catch (e: IOException) {
            println("failed to send")
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
package com.thedirone.multiplayer_tic_tac_toe.core.utils

// Player 1 => X (Server)
// Player 2 => O (Client)
fun isWonGame(player:Int, gameArr: IntArray): Boolean {
    return if(gameArr[0] == player && gameArr[1] == player && gameArr[2] == player) {
        true
    } else if (gameArr[0] == player && gameArr[3] == player && gameArr[6] == player) {
        true
    } else if (gameArr[3] == player && gameArr[4] == player && gameArr[5] == player) {
        true
    } else if (gameArr[6] == player && gameArr[7] == player && gameArr[8] == player) {
        true
    } else if (gameArr[1] == player && gameArr[4] == player && gameArr[7] == player) {
        true
    } else if (gameArr[2] == player && gameArr[5] == player && gameArr[8] == player) {
        true
    } else if (gameArr[0] == player && gameArr[4] == player && gameArr[8] == player) {
        true
    } else gameArr[2] == player && gameArr[4] == player && gameArr[6] == player
}
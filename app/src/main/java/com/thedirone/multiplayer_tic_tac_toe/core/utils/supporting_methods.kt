package com.thedirone.multiplayer_tic_tac_toe.core.utils

fun returnCopiedIntArr(arrToCopy:IntArray): IntArray {
    val copiedArr: IntArray = IntArray(9);
    arrToCopy.forEachIndexed{idx, value ->
        copiedArr[idx] = value
    }
    return  copiedArr
}
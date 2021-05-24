package com.xuanzhi.garbage.utils


import androidx.compose.ui.graphics.Color


object DataUtils {
    val rgbRegex = Regex("RGB (\\d+),(\\d+),(\\d+)")
    val rangeRegex = Regex("RNG \\d (\\d+)")
    val userRegex = Regex("USR (-?\\d)")
    const val MAX_RANGE = 1000

    fun rgb2Color(rgb: IntArray): Color {
        return Color(rgb[0], rgb[1], rgb[2])
    }

    fun matchRgb(msg: String): Pair<Boolean, IntArray?> {
        var rgb: IntArray? = null
        val match = rgbRegex.matchEntire(msg)
        if (match != null) {
            rgb = intArrayOf(
                match.groupValues?.get(1).toInt(),
                match.groupValues?.get(2).toInt(),
                match.groupValues?.get(3).toInt()
            )
            return Pair(true, rgb)
        }
        return Pair(false, rgb)
    }

    fun matchRange(msg: String): Triple<Boolean, Int, Int> {
        var range = -1
        var number = 1
        val match = rangeRegex.matchEntire(msg)
        if (match != null) {
            number = match.groupValues?.get(1).toInt()
            range = match.groupValues?.get(2).toInt()
            return Triple(true, number, range)
        }
        return Triple(false, number, range)
    }

    fun matchUserType(msg: String): Pair<Boolean, Int> {
        var userType: Int = -1
        val match = userRegex.matchEntire(msg)
        if (match != null) {
            userType = match.groupValues?.get(1).toInt()
            return Pair(true, userType)
        }
        return Pair(false, userType)
    }
}

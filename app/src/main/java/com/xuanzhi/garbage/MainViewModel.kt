package com.xuanzhi.garbage

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xuanzhi.garbage.utils.BindFakeCommunicator
import com.xuanzhi.garbage.utils.BindSerialPortCommunicator
import com.xuanzhi.garbage.utils.Communicator
import com.xuanzhi.garbage.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer


@HiltViewModel
class MainViewModel @Inject constructor(
    @BindSerialPortCommunicator private val communicator: Communicator,
) : ViewModel() {

    private var timer: Timer
    var handler: Handler

    var locale: Locale by mutableStateOf(Locale.SIMPLIFIED_CHINESE)
        private set

    var rgb by mutableStateOf<IntArray?>(null)
        private set

    var range1 by mutableStateOf(99999)
        private set

    var range2 by mutableStateOf(99999)
        private set

    var userType by mutableStateOf(-1)
        private set

    init {
        Log.w("VM", "onInit")
        communicator.connect()
        handler = Handler(Looper.getMainLooper()) {
            when (it.what) {
                1 -> {
                    communicator.read()?.forEach { msg ->
                        Log.w("Handler", "receive: $msg")
                        DataUtils.matchRgb(msg).apply {
                            Log.e("rgb", first.toString() + second.toString())
                            if (first){
                                rgb = second
                                return@forEach
                            }
                        }
                        DataUtils.matchRange(msg).let {it ->
                            Log.e("range", it.toString())
                            if (it.first){
                                when(it.second){
                                    1 -> range1 = it.third
                                    2 -> range2 = it.third
                                }
                                return@forEach
                            }
                        }
                        DataUtils.matchUserType(msg).apply {
                            Log.e("usertype", first.toString() + second.toString())
                            if (first){
                                logIn(userType)
                                return@forEach
                            }
                        }
                    }
                }
            }
            false
        }
        timer = fixedRateTimer(period = 500) {
            handler.sendEmptyMessage(1)
        }
    }

    fun sendCommand(command: String) {
        communicator.write(command)
    }

    fun switchLocale(){
        locale = if (locale == Locale.SIMPLIFIED_CHINESE){
            Locale.ENGLISH
        } else {
            Locale.SIMPLIFIED_CHINESE
        }
    }

    fun logIn(userType: Int){
        this.userType = userType
    }

    fun logOut() {
        userType = -1
    }
}
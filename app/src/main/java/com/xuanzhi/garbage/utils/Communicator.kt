package com.xuanzhi.garbage.utils


import android.util.Log
import com.friendlyarm.FriendlyThings.HardwareControler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

const val TAG = "Communicator"

interface Communicator {
    fun connect(){
        Log.w(TAG, "connect")
    }
    fun close(){
        Log.w(TAG, "close")
    }
    fun read(): List<String>?
    fun write(message: String): Boolean{
        Log.w(TAG, "write: $message")
        return true
    }
}

class FakeCommunicator @Inject constructor(): Communicator {
    override fun read(): List<String>? {
//        Log.w(TAG ,"read")
        return null
    }
}

sealed class SerialPort(
    val devName: String,
    val baud: Long=9600,
    val dataBits: Int = 8,
    val stopBits: Int = 1
){
    object COM3: SerialPort("/dev/ttyAMA3")

    override fun toString(): String {
        return "SerialPort(devName='$devName', baud=$baud, dataBits=$dataBits, stopBits=$stopBits)"
    }
}

class SerialPortCommunicator @Inject constructor(): Communicator {
    var devFD: Int = 3

    private var serialPort: SerialPort = SerialPort.COM3
    constructor(serialPort: SerialPort) : this() {
        this.serialPort = serialPort
    }

    private val messageBuilder = StringBuilder()
    private var isStart = false

    override fun connect() {
        devFD = HardwareControler.openSerialPort(
            serialPort.devName,
            serialPort.baud,
            serialPort.dataBits,
            serialPort.stopBits
        )
        if (devFD == -1){
            throw Exception("can't connect to ${serialPort}")
        }
        Log.e(TAG, "SP devFD: $serialPort")
        super.connect()
    }

    override fun close() {
        HardwareControler.close(devFD);
        super.close()
    }

    override fun read(): List<String>? {
        if (HardwareControler.select(devFD, 0, 0) == 1){
            var buffer = ByteArray(512)
            val sizeReceived = HardwareControler.read(devFD, buffer, 512)
            if (sizeReceived > 0){
                val rst =  mutableListOf<String>()
                var strReceived = String(buffer, 0, sizeReceived)
                Log.w(TAG, "SP Received: $strReceived")
                for (ch in strReceived){
                    when(ch){
                        '@' -> if (isStart){
                            if (messageBuilder.isNotEmpty()){
                                rst.add(messageBuilder.toString())
                                messageBuilder.clear()
                                isStart = false
                            }
                        } else {
                            isStart = true
                        }
                        // '\n', '\r', '\t' -> {}
                        else -> if (isStart){
                            messageBuilder.append(ch)
                        }
                    }
                }
                return rst
            }
        }
        return null
    }

    override fun write(message: String): Boolean {
        val sizeSent = HardwareControler.write(devFD, message.toByteArray())
        return super.write(message)
    }
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindFakeCommunicator

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindSerialPortCommunicator

@Module
@InstallIn(SingletonComponent::class)
object CommunicatorModule {
    @BindFakeCommunicator
    @Provides
    fun bindCommunicator(fakeCommunicator: FakeCommunicator): Communicator {
        return fakeCommunicator
    }

    @BindSerialPortCommunicator
    @Provides
    fun bindSerialPortCommunicator(): Communicator {
        return SerialPortCommunicator(SerialPort.COM3)
    }
}

package com.cryptoeconomicslab.furano_common.network

import com.cryptoeconomicslab.furano_core.primitive.Bytes

interface NetworkCallback {
    fun onRecieve(key: Bytes, value: Bytes)
}

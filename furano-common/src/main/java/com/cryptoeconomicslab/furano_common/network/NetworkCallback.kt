package com.cryptoeconomicslab.furano_common.network

import com.cryptoeconomicslab.furano_common.types.Bytes

interface NetworkCallback {
    fun onRecieve(key: Bytes, value: Bytes)
}

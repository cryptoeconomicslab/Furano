package com.cryptoeconomicslab.furano_common.network

import com.cryptoeconomicslab.furano_core.primitive.Bytes

interface Network {
    fun publish(key: Bytes, value: Bytes)

    fun subscribe(key: Bytes, callback: NetworkCallback)

    fun unsubscribe(key: Bytes, callback: NetworkCallback)
}

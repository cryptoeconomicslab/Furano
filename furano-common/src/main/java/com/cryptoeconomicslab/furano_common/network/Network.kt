package com.cryptoeconomicslab.furano_common.network

import com.cryptoeconomicslab.furano_common.types.Bytes

interface Network {
    fun publish(key: Bytes, value: Bytes)

    fun subscribe(key: Bytes, callback: NetworkCallback)

    fun unsubscribe(key: Bytes, callback: NetworkCallback)
}

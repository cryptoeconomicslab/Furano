package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_common.types.Bytes
import kotlinx.coroutines.Deferred

interface KeyValueStore {
    fun getAsync(key: Bytes): Deferred<Bytes>
    fun putAsync(key: Bytes, value: Bytes): Deferred<Bytes>
    fun delAsync(key: Bytes): Deferred<Unit>
    fun bucket(key: Bytes): KeyValueStore
}

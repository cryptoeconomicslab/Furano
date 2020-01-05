package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_core.types.Bytes

interface KeyValueStore {
    fun get(key: Bytes): Bytes
    fun put(key: Bytes, value: Bytes)
    fun del(key: Bytes)
    fun batch(operations: List<BatchOperation>)
    fun iter(bounds: Bytes): Iterator<Map.Entry<Bytes, Bytes>>
    fun bucket(key: Bytes): KeyValueStore
}

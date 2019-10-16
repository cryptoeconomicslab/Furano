package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_common.types.Bytes
import kotlinx.coroutines.Deferred

interface RangeStore {
    fun getAsync(start: Long, end: Long): Deferred<Bytes>
    fun putAsync(start: Long, end: Long, value: Bytes): Deferred<Bytes>
    fun delAsync(start: Long, end: Long): Deferred<Unit>
    fun bucket(key: Bytes): RangeStore
}

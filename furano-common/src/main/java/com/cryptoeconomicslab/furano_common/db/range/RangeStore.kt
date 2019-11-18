package com.cryptoeconomicslab.furano_common.db.range

import com.cryptoeconomicslab.furano_common.types.Bytes

interface RangeStore {
    fun get(start: Long, end: Long): Array<RangeRecord>
    fun put(start: Long, end: Long, value: Bytes)
    fun del(start: Long, end: Long)
    fun bucket(key: Bytes): RangeStore
}

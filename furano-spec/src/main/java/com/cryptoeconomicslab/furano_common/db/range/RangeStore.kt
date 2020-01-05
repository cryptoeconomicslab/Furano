package com.cryptoeconomicslab.furano_common.db.range

import com.cryptoeconomicslab.furano_core.types.Bytes
import java.math.BigInteger

interface RangeStore {
    fun get(start: BigInteger, end: BigInteger): Array<RangeRecord>
    fun put(start: BigInteger, end: BigInteger, value: Bytes)
    fun del(start: BigInteger, end: BigInteger)
    fun bucket(key: Bytes): RangeStore
}

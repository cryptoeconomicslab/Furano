package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_common.types.Bytes

interface WitnessStore {
    fun storeWitnessAsync(key: Bytes, witness: Bytes): Bytes
    fun getWitnessAsync(key: Bytes): Bytes
}

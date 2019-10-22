package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_common.types.Bytes
import kotlinx.coroutines.Deferred

interface WitnessStore {
    fun storeWitnessAsync(key: Bytes, witness: Bytes): Deferred<Bytes>
    fun getWitnessAsync(key: Bytes): Deferred<Bytes>
}

package com.cryptoeconomicslab.furano_core.decider

import com.cryptoeconomicslab.furano_common.types.Bytes
import com.cryptoeconomicslab.furano_common.types.Decision
import kotlinx.coroutines.Deferred

interface Decider {
    fun decide(context: DeciderContext, inputs: List<Bytes>): Deferred<Decision>
}

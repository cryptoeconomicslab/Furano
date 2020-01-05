package com.cryptoeconomicslab.furano_core.decider

import com.cryptoeconomicslab.furano_core.types.Bytes
import com.cryptoeconomicslab.furano_core.types.Decision

interface Decider {
    fun decide(context: DeciderContext, inputs: List<Bytes>): Decision
}

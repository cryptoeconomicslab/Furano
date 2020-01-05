package com.cryptoeconomicslab.furano_core.decider

import com.cryptoeconomicslab.furano_core.types.Bytes

data class DeciderContext(
    val boundedVariables: List<Bytes>
)

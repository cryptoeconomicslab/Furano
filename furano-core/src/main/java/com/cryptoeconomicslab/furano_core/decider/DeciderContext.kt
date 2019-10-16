package com.cryptoeconomicslab.furano_core.decider

import com.cryptoeconomicslab.furano_common.types.Bytes

data class DeciderContext(
    val boundedVariables: List<Bytes>
)

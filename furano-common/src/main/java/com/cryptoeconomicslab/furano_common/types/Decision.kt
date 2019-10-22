package com.cryptoeconomicslab.furano_common.types

data class Decision(
    val outcome: Boolean,
    val implicationProofElements: List<ImplicationProofElement>
)
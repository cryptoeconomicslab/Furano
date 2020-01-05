package com.cryptoeconomicslab.furano_core.types

data class Decision(
    val outcome: Boolean,
    val implicationProofElements: List<ImplicationProofElement>
)

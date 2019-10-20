package com.cryptoeconomicslab.furano_common.types

data class ImplicationProofElement(
    val implication: Property,
    val implicationWitness: Bytes
)

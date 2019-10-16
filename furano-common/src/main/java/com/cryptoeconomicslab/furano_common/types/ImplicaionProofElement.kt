package com.cryptoeconomicslab.furano_common.types

data class ImplicaionProofElement(
    val implication: Property,
    val implicationWitness: Bytes
)

package com.cryptoeconomicslab.furano_common.types

data class ImplicationProofElement(
    val implication: Property,
    val implicationWitness: Bytes
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImplicationProofElement

        if (implication != other.implication) return false
        if (!implicationWitness.contentEquals(other.implicationWitness)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = implication.hashCode()
        result = 31 * result + implicationWitness.contentHashCode()
        return result
    }
}

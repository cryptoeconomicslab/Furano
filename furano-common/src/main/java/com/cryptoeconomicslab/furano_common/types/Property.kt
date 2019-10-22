package com.cryptoeconomicslab.furano_common.types

data class Property(
    val deciderAddress: Address,
    val inputs: List<Bytes>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Property

        if (!deciderAddress.contentEquals(other.deciderAddress)) return false
        if (inputs != other.inputs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = deciderAddress.contentHashCode()
        result = 31 * result + inputs.hashCode()
        return result
    }
}

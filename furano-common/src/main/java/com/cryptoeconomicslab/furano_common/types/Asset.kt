package com.cryptoeconomicslab.furano_common.types

data class Asset(
    val tokenAddress: Address,
    val range: Range
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Asset

        if (!tokenAddress.contentEquals(other.tokenAddress)) return false
        if (range != other.range) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tokenAddress.contentHashCode()
        result = 31 * result + range.hashCode()
        return result
    }
}

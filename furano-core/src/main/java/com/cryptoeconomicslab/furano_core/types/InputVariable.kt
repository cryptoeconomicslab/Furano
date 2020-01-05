package com.cryptoeconomicslab.furano_core.types

data class InputVariable(
    val kind: InputKind,
    val value: Bytes
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InputVariable

        if (kind != other.kind) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = kind.hashCode()
        result = 31 * result + value.contentHashCode()
        return result
    }
}

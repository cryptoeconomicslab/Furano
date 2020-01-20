package com.cryptoeconomicslab.furano_core.primitive

import java.math.BigInteger
import kotlin.collections.List as KList

sealed class Primitive

data class Address(val data: String) : Primitive()

data class BigNumber(val data: BigInteger) : Primitive()

data class Integer(val data: Int) : Primitive()

data class List(val data: KList<Primitive>) : Primitive()

data class Range(
    val start: BigNumber,
    val end: BigNumber
) : Primitive()

data class Struct(val data: Primitive) : Primitive()

data class Bytes(val data: ByteArray) : Primitive() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bytes

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

data class Tuple2(val data: Pair<Primitive, Primitive>) : Primitive()

data class Tuple3(val data: Triple<Primitive, Primitive, Primitive>) : Primitive()

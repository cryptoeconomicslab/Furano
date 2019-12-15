package com.cryptoeconomicslab.furano_common.types

import java.math.BigInteger
import java.nio.charset.Charset

typealias Bytes = ByteArray

fun Bytes.convertToString(): String = this.toString(getCharset())

fun convertFromString(from: String): Bytes = from.toByteArray(getCharset())
fun convertFromBigInteger(from: BigInteger): Bytes = from.toString().toByteArray(getCharset())

private fun getCharset(): Charset = Charset.defaultCharset()

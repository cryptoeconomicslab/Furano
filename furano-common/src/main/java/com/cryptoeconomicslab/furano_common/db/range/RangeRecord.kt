package com.cryptoeconomicslab.furano_common.db.range

import com.beust.klaxon.Converter
import com.beust.klaxon.Json
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import com.cryptoeconomicslab.furano_common.error.FailedDecodeException
import com.cryptoeconomicslab.furano_common.error.UnexpectedParamsException
import com.cryptoeconomicslab.furano_common.types.Bytes
import com.cryptoeconomicslab.furano_common.types.convertFromString
import com.cryptoeconomicslab.furano_common.types.convertToString
import java.math.BigInteger

data class RangeRecord(
    @Json(name = "start")
    val start: BigInteger,
    @Json(name = "end")
    val end: BigInteger,
    @Json(name = "value")
    val value: Bytes
) {
    companion object {
        fun decode(bytes: Bytes): RangeRecord {
            val jsonStr = bytes.toString()

            return Klaxon().converter(RangeRecordConverter())
                .parse<RangeRecord>(jsonStr)
                ?: throw FailedDecodeException("Failed to decode json")
        }
    }

    fun encode(): Bytes {
        val jsonStr = Klaxon().converter(RangeRecordConverter())
            .toJsonString(this)
        return convertFromString(jsonStr)
    }

    fun intersect(start: BigInteger, end: BigInteger): Boolean {
        if (start < BigInteger.ZERO || end < BigInteger.ZERO) throw UnexpectedParamsException("start and end should be not negative")
        if (end < start) throw UnexpectedParamsException("start should not be larger than end")

        /*
        These are the only cases which does not intersect.
        Larger range is the range for db and smaller one is given one
                             ________________
           _____________    |                |
          |             |   |                |
        ----------------------------------------

           _____________
          |             |    ________________
          |             |   |                |
        ----------------------------------------
         */

        val isLeftWithNoIntersect = end <= this.start
        val isRightWithNoIntersect = start >= this.end

        return !isLeftWithNoIntersect && !isRightWithNoIntersect
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RangeRecord

        if (start != other.start) return false
        if (end != other.end) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + value.contentHashCode()
        return result
    }
}

class RangeRecordConverter : Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == RangeRecord::class.java

    override fun fromJson(jv: JsonValue): RangeRecord = RangeRecord(
        start = jv.objString("start").toBigInteger(),
        end = jv.objString("end").toBigInteger(),
        value = convertFromString(jv.objString("value"))
    )

    override fun toJson(value: Any): String {
        val rangeRecord = value as RangeRecord
        return """
            {
                "start" : ${rangeRecord.start},
                "end" : ${rangeRecord.end},
                "value" : ${rangeRecord.value.convertToString()}
            }
        """.trimIndent()
    }

}
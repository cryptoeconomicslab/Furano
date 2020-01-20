package com.cryptoeconomicslab.furano_common.db.range

import com.beust.klaxon.Converter
import com.beust.klaxon.Json
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import com.cryptoeconomicslab.furano_common.error.FailedDecodeException
import com.cryptoeconomicslab.furano_common.error.UnexpectedParamsException
import com.cryptoeconomicslab.furano_core.primitive.Bytes
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
        val jsonStrInBytes = Klaxon().converter(RangeRecordConverter())
            .toJsonString(this)
            .toByteArray()
        return Bytes(
            data = jsonStrInBytes
        )
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
}

class RangeRecordConverter : Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == RangeRecord::class.java

    override fun fromJson(jv: JsonValue): RangeRecord = RangeRecord(
        start = jv.objString("start").toBigInteger(),
        end = jv.objString("end").toBigInteger(),
        value = Bytes(
            data = jv.objString("value").toByteArray()
        )
    )

    override fun toJson(value: Any): String {
        val rangeRecord = value as RangeRecord
        return """
            {
                "start" : ${rangeRecord.start},
                "end" : ${rangeRecord.end},
                "value" : ${rangeRecord.value}
            }
        """.trimIndent()
    }

}
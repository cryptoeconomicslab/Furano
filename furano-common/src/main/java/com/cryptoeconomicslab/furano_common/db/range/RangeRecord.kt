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

data class RangeRecord(
    @Json(name = "start")
    val start: Long,
    @Json(name = "end")
    val end: Long,
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

    fun intersect(start: Long, end: Long): Boolean {
        if (start < 0 || end < 0) throw UnexpectedParamsException("start and end should be not negative")
        if (end < start) throw UnexpectedParamsException("start should not be larger than end")

        // `Range` include end and we don't want to according to the spec
        val requestedRange = LongRange(start, end - 1)
        val recordRange = LongRange(this.start, this.end - 1)

        return requestedRange.intersect(recordRange).isNotEmpty()
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
        start = jv.objInt("start").toLong(),
        end = jv.objInt("start").toLong(),
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
package com.cryptoeconomicslab.furano_android.db

import com.cryptoeconomicslab.furano_common.db.BatchOperation
import com.cryptoeconomicslab.furano_common.db.KeyValueStore
import com.cryptoeconomicslab.furano_common.db.range.RangeRecord
import com.cryptoeconomicslab.furano_common.db.range.RangeStore
import com.cryptoeconomicslab.furano_common.types.Bytes
import com.cryptoeconomicslab.furano_common.types.convertFromBigInteger
import java.math.BigInteger


class RangeDb(val kvs: KeyValueStore) : RangeStore {
    override fun get(start: BigInteger, end: BigInteger): Array<RangeRecord> {
        val iter = kvs.iter(convertFromBigInteger(start))
        val keyValue = if (iter.hasNext()) {
            iter.next()
        } else {
            null
        } ?: return emptyArray()

        val results = mutableListOf<RangeRecord>()
        var rangeRecord = RangeRecord.decode(keyValue.value)
        while (rangeRecord.intersect(start, end)) {
            results.add(rangeRecord)
            val keyValue = if (iter.hasNext()) {
                iter.next()
            } else {
                null
            } ?: break
            rangeRecord = RangeRecord.decode(keyValue.value)
        }
        return results.toTypedArray()
    }

    override fun put(start: BigInteger, end: BigInteger, value: Bytes) {
        val inputRanges = this.delBatch(start, end)
        val outputRanges = mutableListOf<RangeRecord>()

        if (inputRanges.isNotEmpty()) {
            val firstRangeRecord = inputRanges.first()
            if (firstRangeRecord.start < start) {
                outputRanges.add(
                    RangeRecord(
                        start = firstRangeRecord.start,
                        end = start,
                        value = firstRangeRecord.value
                    )
                )
            } else {
                val lastRangeRecord = inputRanges.last()
                if (end < lastRangeRecord.end) {
                    outputRanges.add(
                        RangeRecord(
                            start = end,
                            end = lastRangeRecord.end,
                            value = lastRangeRecord.value
                        )
                    )
                }
            }
        }

        outputRanges.add(
            RangeRecord(
                start = start,
                end = end,
                value = value
            )
        )

        this.putBatch(outputRanges.toTypedArray())
    }

    override fun del(start: BigInteger, end: BigInteger) {
        this.delBatch(start, end)
    }

    override fun bucket(key: Bytes): RangeStore = RangeDb(this.kvs)

    private fun putBatch(ranges: Array<RangeRecord>) {
        val operations = ranges.map {
            BatchOperation.PutBatchOperation(
                key = convertFromBigInteger(it.end),
                values = it.encode()
            )
        }

        this.kvs.batch(operations = operations)
    }

    private fun delBatch(start: BigInteger, end: BigInteger): Array<RangeRecord> {
        val deletedTargets = this.get(start, end)
        val operations = deletedTargets.map {
            BatchOperation.DelBatchOperation(
                key = convertFromBigInteger(it.end)
            )
        }

        this.kvs.batch(operations)
        return deletedTargets
    }

}
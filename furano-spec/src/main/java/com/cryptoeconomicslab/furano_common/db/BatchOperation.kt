package com.cryptoeconomicslab.furano_common.db

import com.cryptoeconomicslab.furano_core.primitive.Bytes

sealed class BatchOperation {

    abstract val type: OperationType
    abstract val key: Bytes

    class PutBatchOperation(
        override val key: Bytes,
        val values: Bytes
    ) : BatchOperation() {
        override val type: OperationType = OperationType.PUT
    }

    class DelBatchOperation(
        override val key: Bytes
    ) : BatchOperation() {
        override val type: OperationType = OperationType.DELETE
    }
}

enum class OperationType(val type: String) {
    PUT("Put"),
    DELETE("Del")
}

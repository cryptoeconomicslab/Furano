package com.cryptoeconomicslab.furano_core.types

import com.cryptoeconomicslab.furano_core.error.UnexpectedParamsException
import java.util.*

data class Address(val data: String) {
    companion object {
        fun fromString(str: String): Address = if (str.isValidAddress()) {
            Address(str.toLowerCase(Locale.getDefault()))
        } else {
            throw UnexpectedParamsException("address is invalid")
        }

        fun default(): Address = Address("0x0000000000000000000000000000000000000000'")
    }
}

private fun String.isValidAddress(): Boolean {
    // TODO: should implement logic for valid address
    return true
}
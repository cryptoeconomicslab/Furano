package com.cryptoeconomicslab.furano_core.coder

import com.cryptoeconomicslab.furano_core.types.Bytes

/**
 * Coder type
 * L1 specific ABI encoder/decoder must implement this interface.
 * for example, Coder for ethereum can be implemented using web3.js or ethers.js
 */
interface Coder {
    fun encode(input: Codable): Bytes
    fun <T : Codable> decode(d: T, data: Bytes): T
}

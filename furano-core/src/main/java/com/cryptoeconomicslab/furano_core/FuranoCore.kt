package com.cryptoeconomicslab.furano_core

import com.cryptoeconomicslab.furano_contract.FuranoContract

object FuranoCore : FuranoContract {
    override fun hello(): String = "Hello from Furano Core"
}
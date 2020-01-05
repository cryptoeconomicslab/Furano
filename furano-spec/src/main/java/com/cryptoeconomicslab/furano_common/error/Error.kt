package com.cryptoeconomicslab.furano_common.error

class FailedDecodeException(className: String) : Exception("Failed to decode $className")

class UnexpectedParamsException(errorMessage: String): Exception(errorMessage)

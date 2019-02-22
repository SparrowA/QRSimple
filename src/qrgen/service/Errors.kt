package qrgen.service

import java.lang.RuntimeException

class IncorrectEncodingChar(message: String?) : RuntimeException(message)

class NegativeArrayIndex(message: String?) : RuntimeException(message)

class IncorrectBitCount(message: String?) : RuntimeException(message)
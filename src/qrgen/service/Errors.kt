package qrgen.service

import java.lang.RuntimeException

class IncorrectEncodingChar(message: String?) : RuntimeException(message)
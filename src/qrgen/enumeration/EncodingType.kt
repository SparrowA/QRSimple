package qrgen.enumeration

enum class EncodingType(val value : Byte) {
    Numeric(1.toByte()),
    Alphanumeric(2.toByte()),
    Binary(4.toByte())
}
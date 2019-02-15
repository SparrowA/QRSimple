package qrgen.enumeration

enum class EncodingType(val value : Byte) {
    Numeric(1.toByte()) {
        override fun getLengthOfSize(levelQr : Byte) =
                when(levelQr) {
                    in 1..9 -> 10
                    in 10..26 -> 12
                    in 27..40 -> 14
                    else -> 0
                }.toByte()
    },
    Alphanumeric(2.toByte()) {
        override fun getLengthOfSize(levelQr : Byte) =
            when(levelQr) {
                in 1..9 -> 9
                in 10..26 -> 11
                in 27..40 -> 13
                else -> 0
            }.toByte()
    },
    Binary(4.toByte()) {
        override fun getLengthOfSize(levelQr : Byte) =
            when(levelQr) {
                in 1..9 -> 8
                in 10..26 -> 16
                in 27..40 -> 16
                else -> 0
            }.toByte()
    };

    abstract fun getLengthOfSize(levelQr: Byte) : Byte
}
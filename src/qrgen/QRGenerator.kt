package qrgen

import qrgen.enumeration.EncodingType
import qrgen.enumeration.ErrorCorrection
import qrgen.service.CharTable
import qrgen.service.Settings

object QRGenerator {

    /**
     * @param data Data string for encoding
     * @param errLevel Level of error. Can be  L = 7%, M = 15%, Q = 25%, H = 30%
     * @param type Type of encoding. Numeric - 1, Alphanumeric - 2, Binary - 4
     */
    fun getQRCode(data : String, errLevel : ErrorCorrection = ErrorCorrection.High, type : EncodingType = EncodingType.Alphanumeric) {
        val encodeByte = ArrayList<Byte>()

        //encodeByte.addAll(getEncodingValue(data, type))
    }

    /**
     * Encoding string to array of byte using specific table
     */
    private fun getEncodingValue(data : String, type: EncodingType) =
        when(type) {
            EncodingType.Binary -> data.flatMap { getBitArrayByNumber(it.toInt(), 8) }
            EncodingType.Alphanumeric -> data.chunked(2).flatMap {
                val charTable = CharTable()
                val num = when(it.length) {
                    2 -> charTable.getCharCode(it[0]) * 45 + charTable.getCharCode(it[1])
                    1 -> charTable.getCharCode(it[0])
                    else -> 0
                }
                getBitArrayByNumber(num, 11)
            }
            EncodingType.Numeric -> data.chunked(3).associate { Pair(it.toInt(), it.length) } .flatMap {
                getBitArrayByNumber(it.key,
                    when(it.value) {
                        3 -> 10
                        2 -> 7
                        1 -> 4
                        else -> 0
                    }
                )
            }
        }

    /**
     * Transform Int to its bit array
     * @param number Int to transform
     * @param size Count of bit in array. If count fo bit less then size, it will be padding leading 0
     */
    private fun getBitArrayByNumber(number : Int, size : Int ) : BitBlock {
        val result = BitBlock(size.toByte())

        var buff = number
        for(i in result.size downTo 0) {
            result[i] = buff % 2 == 1
            buff /= 2
        }

        return result
    }
}
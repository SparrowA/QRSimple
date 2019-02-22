package qrgen

import qrgen.Model.BitBlock
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

    fun pow(base : Int, exp : Int) : Int = if(exp == 0) 1 else base * pow(base, exp - 1)

    /**
     * Calc count of byte in each blocks
     */
    private fun getBlocksSize(countOfByte : Int, countOfBlock : Int) : Array<Int> {
        val minByte = countOfByte / countOfBlock
        val result = Array(countOfBlock) {minByte}

        var remainder = countOfByte - minByte * countOfBlock

        if(remainder > 0) {
            (result.size - 1..0).forEach label@{
                result[it] += 1
                remainder--

                if(remainder == 0) {
                    return@label
                }
            }
        }

        return result
    }

    /**
     * Encoding string to array of byte using specific table
     */
    private fun getEncodingValue(data : String, type: EncodingType) =
        when(type) {
            EncodingType.Binary -> data.flatMap { BitBlock(8).fillBlockByNum(it.toInt()).toBooleanList() }
            EncodingType.Alphanumeric -> data.chunked(2).flatMap {
                val charTable = CharTable()
                val num = when(it.length) {
                    2 -> charTable.getCharCode(it[0]) * 45 + charTable.getCharCode(it[1])
                    1 -> charTable.getCharCode(it[0])
                    else -> 0
                }
                BitBlock(11).fillBlockByNum(num).toBooleanList()
            }
            EncodingType.Numeric -> data.chunked(3).associate { Pair(it.toInt(), it.length) } .flatMap {
                BitBlock(
                    when (it.value) {
                        3 -> 10
                        2 -> 7
                        1 -> 4
                        else -> 0
                    }
                ).fillBlockByNum(it.key).toBooleanList()
            }
        }

    private fun getZeroBitBlock(size : Byte) = BitBlock(size).fillBlockByZero().toBooleanList()
}
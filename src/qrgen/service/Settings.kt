package qrgen.service

import qrgen.Model.BitBlock
import qrgen.enumeration.EncodingType
import qrgen.enumeration.ErrorCorrection

/**
 * Class with technical field of
 */
class Settings(errLevel : ErrorCorrection, type : EncodingType, countEncodBit : Int) {

    /**
     * Technical field with count of bits
     */
    private val sizeBits : BitBlock

    /**
     * Level of QR code
     */
    private var qrLevel : Byte

    /**
     * Type of data to encoding in QR code
     */
    private val typeCode : BitBlock = BitBlock(4).fillBlockByNum(type.value.toInt())

    /**
     * Capacity of qr code level
     */
    val levelSize : Short

    init {
        val levelOfQR = QRLevel(errLevel)
        qrLevel = levelOfQR.getLevelByCapacity(countEncodBit + 3)

        val lengthOfSize = type.getLengthOfSize(qrLevel)
        sizeBits = BitBlock(lengthOfSize).fillBlockByNum(countEncodBit)

        levelSize = levelOfQR.getCapacityByLevel(qrLevel)

        //TODO: Need write code to correct qr level depend on tech bit
    }

    fun getTechBlock() : List<Boolean> {
        val result = arrayListOf<Boolean>()
        result.addAll(typeCode.toBooleanList())
        result.addAll(sizeBits.toBooleanList())
        return result
    }

}
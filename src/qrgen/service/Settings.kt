package qrgen.service

import qrgen.BitBlock
import qrgen.enumeration.EncodingType
import qrgen.enumeration.ErrorCorrection

/**
 * Class with technical field of
 */
class Settings(errLevel : ErrorCorrection, type : EncodingType, countEncodBit : Int) {

    /**
     * Technical field with count of bits
     */
    val sizeBits : BitBlock

    /**
     * Level of QR code
     */
    var qrLevel : Byte
        private set

    /**
     * Type of data to encoding in QR code
     */
    val typeCode : BitBlock = BitBlock(4).fillBlock(type.value.toInt())

    init {
        val levelOfQR = QRLevel(errLevel)
        qrLevel = levelOfQR.getLevelByCapacity(countEncodBit + 3)

        val lengthOfSize = type.getLengthOfSize(qrLevel)
        sizeBits = BitBlock(lengthOfSize).fillBlock(countEncodBit)

        //TODO: Need write code to correct qr level depend on tech bit
    }
}
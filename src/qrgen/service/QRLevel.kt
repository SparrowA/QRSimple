package qrgen.service

import qrgen.enumeration.ErrorCorrection

/**
 * Define level of QR code by level of correction of error and total amount of necessary capacity
 */
class QRLevel(errorCorrection: ErrorCorrection) {

    /**
     * Capacity is created by level of correction of error
     */
    private val listOfCapacity : Array<Short> = when(errorCorrection) {
        ErrorCorrection.Low -> arrayOf(
            152, 272, 440, 640, 864, 1088, 1248, 1552,
            1856, 2192, 2592, 2960, 3424, 3688, 4184, 4712,
            5176, 5768, 6360, 6888, 7456, 8048, 8753, 9392,
            10208, 10960, 11744, 12248, 13048, 13880, 14744, 15640,
            16568, 17528, 18448, 19472, 20528, 21616, 22496, 23648
        )
        ErrorCorrection.Medium -> arrayOf(
            128, 224, 352, 512, 688, 864, 992, 1232,
            1456, 1728, 2032, 2320, 2672, 2920, 3320, 3624,
            4056, 4504, 5016, 5352, 5712, 6256, 6880, 7312,
            8000, 8496, 9024, 9544, 10136, 10984, 11640, 12328,
            13048, 13800, 14496, 15312, 15936, 16816, 17728
        )
        ErrorCorrection.Quartile -> arrayOf(
            104, 176, 272, 384, 496, 608, 704, 880,
            1056, 1232, 1440, 1648, 1952, 2088, 2360, 2600,
            2936, 3176, 3560, 3880, 4096, 4544, 4912, 5312,
            5744, 6032, 6464, 6968, 7288, 7880, 8264, 8920,
            9368, 9848, 10288, 10832, 11408, 12016, 12656, 13328
        )
        ErrorCorrection.High -> arrayOf(
            72, 128, 208, 288, 368, 480, 528, 688,
            800, 976, 1120, 1264, 1440, 1576, 1784, 2024,
            2264, 2504, 2728, 3080, 3248, 3536, 3712, 4112,
            4304, 4768, 5024, 5288, 5608, 5960, 6344, 6780,
            7208, 7688, 7888, 8432, 8768, 9136, 9776, 10208
        )
    }

    /**
     * Count of block by level of QR code
     */
    private val countOfBlock : Array<Byte> = when(errorCorrection) {
        ErrorCorrection.Low -> arrayOf(
            1, 1, 1, 1, 1, 2, 2, 2,
            2, 4, 4, 4, 4, 4, 6, 6,
            6, 6, 7, 8, 8, 9, 9, 10,
            12, 12, 12, 13, 14, 15, 16, 17,
            18, 19, 19, 20, 21, 22, 24, 25
        )
        ErrorCorrection.Medium -> arrayOf(
            1, 1, 1, 2, 2, 4, 4, 4,
            5, 5, 5, 8, 9, 9, 10, 10,
            11, 13, 14, 16, 17, 17, 18, 20,
            21, 23, 25, 26, 28, 29, 31, 33,
            35, 37, 38, 40, 43, 45, 47, 49
        )
        ErrorCorrection.Quartile -> arrayOf(
            1, 1, 2, 2, 4, 4, 6, 6,
            8, 8, 8, 10, 12, 16, 12, 17,
            16, 18, 21, 20, 23, 23, 25, 27,
            29, 34, 34, 35, 38, 40, 43, 45,
            48, 51, 53, 56, 59, 62, 65, 68
        )
        ErrorCorrection.High -> arrayOf(
            1, 1, 2, 4, 4, 4, 5, 6,
            8, 8, 11, 11, 16, 16, 18, 16,
            19, 21, 25, 25, 25, 34, 30, 32,
            35, 37, 40, 42, 45, 48, 51, 54,
            57, 60, 63, 66, 70, 74, 77, 81
        )
    }

    /**
     * Return level of QR code by total amount of necessary capacity
     */
    fun getLevelByCapacity(capacity: Int) = (listOfCapacity.indexOfFirst { it >= capacity } + 1).toByte()

    /**
     * Return capacity for qr level
     */
    fun getCapacityByLevel(level : Int) = if(level - 1 < 0 || level - 1 >= listOfCapacity.size) throw IndexOutOfBoundsException("Level must be between 1 and 40") else listOfCapacity[level - 1]

    /**
     * Return count block by QR code level
     */
    fun getCountBlockByLevel(level : Int) = if(level in (1..countOfBlock.size)) countOfBlock[level - 1] else throw IndexOutOfBoundsException("Level must be between 1 and 40")
}

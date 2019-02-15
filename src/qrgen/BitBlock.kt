package qrgen

class BitBlock(size : Byte) {
    val bits = Array(size.toInt()) { false }

    /**
     * Transform Int to its bit array
     * @param num Int to transform
     */
    fun fillBlock(num : Int) : BitBlock {
        var buff = num
        for(i in bits.size downTo 0) {
            bits[i] = buff % 2 == 1
            buff /= 2
        }
        return this
    }
}
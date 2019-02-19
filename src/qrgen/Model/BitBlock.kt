package qrgen.Model

import qrgen.service.NegativeArrayIndex
import java.lang.IndexOutOfBoundsException

class BitBlock(val size : Byte) : Iterable<Boolean> {

    companion object {
        //11101100
        private val fillerOne = BitBlock(8.toByte()).fillBlockByNum(236)
        //00010001
        private val fillerTwo = BitBlock(8.toByte()).fillBlockByNum(17)

        /**
         * Return list of filler byte
         */
        fun getFillers(byteCount : Short) : List<BitBlock> =
            (1..byteCount).map {
                if(it % 2 != 0) fillerOne
                else fillerTwo
            }
    }

    private val bits = Array(size.toInt()) { false }

    class BitBlockIterator(private val bitBlock: BitBlock) : Iterator<Boolean> {

        private var curIndex = 0

        override fun hasNext() = curIndex in 0..(bitBlock.size - 1)

        override fun next() = bitBlock[curIndex]
    }

    override fun iterator() = BitBlockIterator(this)

    operator fun get(index : Int) : Boolean = if(checkIndex(index)) bits[index] else false

    operator fun set(index: Int, value : Boolean) = if(checkIndex(index)) bits[index] = value else throw UnknownError("Something've gone wrong ")

    private fun checkIndex(index : Int) : Boolean =
        when {
            index in 0..(size - 1) -> true
            index < 0 -> throw NegativeArrayIndex("Index can't be negative $index")
            index >= size -> throw  IndexOutOfBoundsException("Index must be between 0 and ${bits.size}")
            else -> false
        }

    /**
     * Transform Int to its bit array
     * @param num Int to transform
     */
    fun fillBlockByNum(num : Int) : BitBlock {
        var buff = num
        for(i in bits.size downTo 0) {
            bits[i] = buff % 2 == 1
            buff /= 2
        }
        return this
    }

    /**
     *
     */
    fun fillBlockByZero() : BitBlock {
        for(i in 0..bits.size) {
            bits[i] = false
        }
        return this
    }

    fun toBooleanList() = bits.toList()
}
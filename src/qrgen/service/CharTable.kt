package qrgen.service

object CharTable {

    const val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:"

    const val numbers = "0123456789"

    fun getCharCode(char: Char) : Byte = if(numbers.contains(char)) char.toByte() else (letters.indexOf(char) + 10).toByte()
}
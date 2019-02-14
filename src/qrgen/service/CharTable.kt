package qrgen.service

class CharTable {

    private val letters = ('0'..'9').plus('A'..'Z').plus(" \$%*+-./:")

    fun getCharCode(char: Char) = letters.indexOf(char)
}

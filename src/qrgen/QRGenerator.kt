package qrgen

object QRGenerator {

    /**
     * @param data Data string for encoding
     * @param errLevel Level of error. Can be  L = 7%, M = 15%, Q = 25%, H = 30%
     * @param type Type of encoding. Number - 1, Alphanumber - 2, Binary - 4
     */
    fun getQRCode(data : String, errLevel : Char = 'H', type : Byte = 2) {
        val encodeByte = ArrayList<Byte>()

        if(type == 4.toByte()) {

        }
    }
}
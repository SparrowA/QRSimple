package qrgen.enumeration

enum class ErrorCorrection(val percent : Int) {
    Low(7),
    Medium(15),
    Quartile(25),
    High(30)
}
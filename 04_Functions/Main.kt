fun main() {
    val encodeStr = "F2p)v\"y233{0->c}ttelciFc"
    val encodeStrFirst = encodeStr.substring(0, encodeStr.length/2)
    val encodeStrSecond = encodeStr.substring(encodeStr.length/2)
    println("Encrypted string: $encodeStr, length ${encodeStr.length}")
    println("One part of encrypted string: $encodeStrFirst")
    println("Two part of encrypted string: $encodeStrSecond")
    val decodeStrSecond = processingTwoPart(encodeStrSecond)
    println("Two part of decrypted string: $decodeStrSecond")
    val decodeStrFirst = processingOnePart(encodeStrFirst)
    println("One part of decrypted string: $decodeStrFirst")
    val decodeStr = decodeStrFirst + decodeStrSecond
    println("Original decrypted string: $decodeStr, length ${decodeStr.length}")
}
fun processingOnePart (str : String) : String {
    //2.5
    var strMap = shift(str, 1) {strInput, numInput -> strInput.map {it + numInput}.joinToString("")}
    //2.4
    var strReplace = strMap.replace('5', 's')
    //2.3
    strReplace = strReplace.replace('4', 'u')
    //2.2
    strMap = shift(strReplace, -3) {strInput, numInput -> strInput.map {it + numInput}.joinToString("")}
    //2.1
    //return of the result
    return strMap.replace('0', 'o')
}

//  processing function for the second part
fun processingTwoPart (str : String) : String {
    //3.3
    val strReversed = str.reversed()
    //3.2
    val strMap = shift(strReversed, -4) {strInput, numInput -> strInput.map {it + numInput}.joinToString("")}
    //3.1
    //return of the result
    return strMap.replace('_',' ')
}
// shift function
fun shift(str : String, num : Int, funMap: (strInput : String, numInput : Int) -> String) : String {
    return funMap(str, num)
}

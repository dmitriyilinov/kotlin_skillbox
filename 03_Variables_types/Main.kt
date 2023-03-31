fun main() {
    val firstName : String = "Dmitriy"
    val lastName: String = "Ilinov"
    var heigth: Double = 1.89
    var weigth: Float = 89f
    var isChild: Boolean = heigth < 1.50 && weigth < 40f
    var info: String = "My name is $firstName $lastName. My heigth is $heigth m, my weigth is $weigth kg. Am I a child? - $isChild."
    println(info)
    heigth = 1.75
    isChild = heigth < 1.50 && weigth < 40f
    info = "My name is $firstName $lastName. My heigth is $heigth m, my weigth is $weigth kg. Am I a child? - $isChild."
    println(info)
    heigth = 1.40
    weigth = 30f
    isChild = heigth < 1.50 && weigth < 40f
    info = "My name is $firstName $lastName. My heigth is $heigth m, my weigth is $weigth kg. Am I a child? - $isChild."
    println(info)
}
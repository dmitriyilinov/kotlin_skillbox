enum class Product(val weight: Int, val duration: Int, val type: TypeProduct) {
    BREAD(10, 2, TypeProduct.FOOD),
    MILK(20, 2, TypeProduct.FOOD),
    SHAMPOO(20, 3, TypeProduct.LOW),
    SOAP(20, 3, TypeProduct.LOW),
    PAINT(30, 4, TypeProduct.MEDIUM),
    GLUE(30, 4, TypeProduct.MEDIUM),
    WALLPAPER(40, 5, TypeProduct.LARGE),
    LAMINATE(40, 5, TypeProduct.LARGE)
}

enum class TypeProduct {
    LARGE, MEDIUM, LOW, FOOD
}
object BrandTV {
    private val mapOfBrand =
        mapOf(1 to "LG", 2 to "Samsung", 3 to "Xiaomi", 4 to "Philips", 5 to "HYUNDAI")
    private val mapOfMaxCountChannel =
        mapOf("LG" to 10, "Samsung" to 8, "Xiaomi" to 10, "Philips" to 5, "HYUNDAI" to 7)
    private val mapOfModel =
        mapOf("LG" to "NanoCell", "Samsung" to "UE", "Xiaomi" to "MI TV", "Philips" to "PUS", "HYUNDAI" to "H-LED")
    private val mapOfDiagonal =
        mapOf("LG" to 65, "Samsung" to 50, "Xiaomi" to 55, "Philips" to 32, "HYUNDAI" to 43)

    fun getMaxCountChannel(brand: String) = if (mapOfMaxCountChannel.containsKey(brand)) {
        mapOfMaxCountChannel.getValue(brand)
    } else 0

    fun getBrand(n: Int) = if (mapOfBrand.containsKey(n)) {
        mapOfBrand.getValue(n)
    } else ""

    fun getModel(brand: String) = if (mapOfModel.containsKey(brand)) {
        mapOfModel.getValue(brand)
    } else ""

    fun getDiagonal(brand: String) = if (mapOfDiagonal.containsKey(brand)) {
        mapOfDiagonal.getValue(brand)
    } else 0


}
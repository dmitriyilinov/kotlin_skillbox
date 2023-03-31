sealed class FireType {
    object SingleGunfire : FireType()
    data class ManyGunfire(val size: Int) : FireType()
}
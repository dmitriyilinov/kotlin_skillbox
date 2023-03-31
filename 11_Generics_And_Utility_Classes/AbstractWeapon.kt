import java.lang.Integer.min
import java.util.Stack

abstract class AbstractWeapon(
    val sizeStore: Int,
    val fireType: FireType,
    var store: Stack<Ammo> = Stack(),
    var available: Boolean = store.isEmpty()
) {
    abstract fun creatingPatron(): Ammo
    open fun rechargeStore() {
        store = Stack()
        for (i in 1..sizeStore) store.push(creatingPatron())
        available = store.isEmpty()
    }

    open fun getPatron(): Stack<Ammo> {
        val patronForGunfire: Stack<Ammo> = Stack()
        if (!available) {
            when (fireType) {
                is FireType.SingleGunfire -> {
                    patronForGunfire.push(store.pop())
                }

                is FireType.ManyGunfire -> {
                    for (i in 1..min(sizeStore, (fireType as FireType.ManyGunfire).size))
                        patronForGunfire.push(store.pop())
                }
            }
            available = store.isEmpty()
        } else throw NoAmmoException()
        return patronForGunfire
    }

    class NoAmmoException : Throwable()
}
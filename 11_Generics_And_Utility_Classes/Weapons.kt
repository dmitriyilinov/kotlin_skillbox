object Weapons {

    fun createPistol(): AbstractWeapon {
        return object : AbstractWeapon(1, FireType.SingleGunfire) {
            override fun creatingPatron(): Ammo {
                return Ammo.BULLET1
            }
        }
    }

    fun createGun(): AbstractWeapon {
        return object : AbstractWeapon(5, FireType.ManyGunfire(5)) {
            override fun creatingPatron(): Ammo {
                return Ammo.BULLET2
            }
        }
    }

    fun createMachineGun(): AbstractWeapon {
        return object : AbstractWeapon(10, FireType.ManyGunfire(10)) {
            override fun creatingPatron(): Ammo {
                return Ammo.BULLET3
            }

        }
    }
}
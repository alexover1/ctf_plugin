package org.overgroup.ctf.abilities

object ItemAbilityRegistry {
    private val abilities = listOf(
        FireWandAbility,
        LevitationWandAbility,
        ShadowstepAbility,
    )

    private val abilityMap = abilities.associateBy { it.name }

    fun get(name: String): ItemAbility? = abilityMap[name]
}

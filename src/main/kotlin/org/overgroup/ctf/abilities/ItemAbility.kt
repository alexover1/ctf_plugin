package org.overgroup.ctf.abilities

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface ItemAbility {
    val name: String
    fun use(player: Player, item: ItemStack)
}

package org.overgroup.ctf.abilities

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.overgroup.ctf.listeners.getItemNameAsString

interface ItemAbility {
    val name: String
    fun use(player: Player, item: ItemStack)

    fun matches(item: ItemStack): Boolean = item.getItemNameAsString().equals(name)
}

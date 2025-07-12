package org.overgroup.ctf.commands

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import net.kyori.adventure.text.Component
import org.overgroup.ctf.Keys

fun fireWandItemCommand(player: Player) {
    val item = ItemStack(Material.BLAZE_ROD)
    val meta = item.itemMeta

    meta.itemName(Component.text("Fire Wand"))
    meta.persistentDataContainer.set(Keys.FIRE_WAND, PersistentDataType.BYTE, 1)
    item.itemMeta = meta

    player.inventory.addItem(item)
}

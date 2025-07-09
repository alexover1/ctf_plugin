package org.overgroup.ctf.listeners

import org.bukkit.Tag
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.TranslatableComponent

fun ItemStack.getItemNameAsString(): String? {
    if (itemMeta.hasItemName()) {
        return itemMeta.itemName().getInnerContent()
    }
    return null
}

fun Component.getInnerContent(): String? = when (this) {
    is TextComponent -> content()
    is TranslatableComponent -> key()
    else -> null
}

private val interactableTags = arrayOf(Tag.DOORS, Tag.TRAPDOORS, Tag.BUTTONS, Tag.BEDS, Tag.SIGNS, Tag.FENCE_GATES, Tag.ANVIL, Tag.SHULKER_BOXES)

fun Material.hasBlockInteraction(): Boolean {
    return when (this) {
        Material.CHEST -> return true
        Material.TRAPPED_CHEST -> return true
        Material.BARREL -> return true
        Material.FURNACE -> return true
        Material.SMOKER -> return true
        Material.BLAST_FURNACE -> return true
        Material.LECTERN -> return true
        Material.SMITHING_TABLE -> return true
        Material.FLETCHING_TABLE -> return true
        Material.CARTOGRAPHY_TABLE -> return true
        Material.STONECUTTER -> return true
        Material.BREWING_STAND -> return true
        Material.LOOM -> return true
        Material.BELL -> return true
        Material.BEACON -> return true
        Material.CRAFTING_TABLE -> return true
        Material.ENCHANTING_TABLE -> return true
        Material.CRAFTER -> return true
        Material.HOPPER -> return true
        Material.DISPENSER -> return true
        Material.DROPPER -> return true
        Material.REPEATER -> return true
        Material.COMPARATOR -> return true
        Material.LEVER -> return true
        Material.DAYLIGHT_DETECTOR -> return true
        Material.NOTE_BLOCK -> return true
        Material.COMMAND_BLOCK -> return true
        Material.CHAIN_COMMAND_BLOCK -> return true
        Material.REPEATING_COMMAND_BLOCK -> return true
        else -> interactableTags.any { it.isTagged(this) }
    }
}

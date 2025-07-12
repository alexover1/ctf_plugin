package org.overgroup.ctf.listeners

import org.bukkit.Tag
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.TranslatableComponent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.overgroup.ctf.abilities.*

const val FIRE_WAND = "Fire Wand"
const val LEVITATION_WAND = "Levitation Wand"
const val HEAL_BEAM = "Heal Beam"
const val HUNTERS_EYE = "Hunter's Eye"
const val SHADOWSTEP = "Shadowstep"
const val WOLF_HORN = "Wolf Horn"
const val BATTLE_BUGLE = "Battle Bugle"

val abilities = mapOf(
    FIRE_WAND to ::fireWandAbility,
    LEVITATION_WAND to ::levitationWandAbility,
    HEAL_BEAM to ::healBeamAbility,
    HUNTERS_EYE to ::huntersEyeAbility,
    SHADOWSTEP to ::shadowStepAbility,
    WOLF_HORN to ::wolfHornAbility,
    BATTLE_BUGLE to ::battleBugleAbility,
)

class ItemUseListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return
        if (event.action.isRightClick) {
            event.clickedBlock?.let {
                if (it.type.hasBlockInteraction()) {
                    return
                }
            }
            if (!player.hasCooldown(item)) {
                item.getItemNameAsString()?.let { abilities[it]?.invoke(player, item) }
            }
        }
    }
}

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

package org.overgroup.ctf.listeners

import org.overgroup.ctf.Keys
import org.overgroup.ctf.abilities.*

import org.bukkit.Tag
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

val abilities = mapOf(
    Keys.FIRE_WAND to ::fireWandAbility,
    Keys.LEVITATION_WAND to ::levitationWandAbility,
    Keys.HEAL_BEAM to ::healBeamAbility,
    Keys.HUNTERS_EYE to ::huntersEyeAbility,
    Keys.SHADOWSTEP to ::shadowStepAbility,
    Keys.WOLF_HORN to ::wolfHornAbility,
    Keys.BATTLE_BUGLE to ::battleBugleAbility,
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
                val meta = item.itemMeta ?: return
                val container = meta.persistentDataContainer

                for (key in container.keys) {
                    abilities[key]?.invoke(player, item)
                }
            }
        }
    }
}

private val interactableTags = arrayOf(Tag.DOORS, Tag.TRAPDOORS, Tag.BUTTONS, Tag.BEDS, Tag.SIGNS, Tag.FENCE_GATES, Tag.ANVIL, Tag.SHULKER_BOXES)

fun Material.hasBlockInteraction(): Boolean {
    return when (this) {
        Material.CHEST -> return true
        Material.ENDER_CHEST -> return true
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

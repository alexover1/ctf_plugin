package org.overgroup.ctf.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component
import org.bukkit.persistence.PersistentDataType
import org.overgroup.ctf.Keys

class FireWandCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as? Player
        if (player != null) {
            val item = ItemStack(Material.BLAZE_ROD)
            val meta = item.itemMeta
            meta.itemName(Component.text("Fire Wand"))
            meta.persistentDataContainer.set(Keys.CUSTOM_COMMAND, PersistentDataType.STRING, "say hello!")
            item.itemMeta = meta
            player.inventory.addItem(item)
        }
        return true
    }
}

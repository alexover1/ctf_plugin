package org.overgroup.ctf.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DropSheepCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as? Player
        if (player != null) {
            val lastPassenger = generateSequence(player.passengers.lastOrNull()) { it.passengers.lastOrNull() }.lastOrNull()
            if (lastPassenger != null) {
                player.removePassenger(lastPassenger)
            }
        }
        return true
    }
}
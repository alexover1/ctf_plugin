package org.overgroup.ctf.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep

class SheepHatCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as? Player
        if (player != null) {
            val sheep = player.world.spawn(player.location, Sheep::class.java)
            val lastPassenger = generateSequence(player.passengers.lastOrNull()) { it.passengers.lastOrNull() }.lastOrNull()
            val addTo = lastPassenger ?: player
            addTo.addPassenger(sheep)
        }
        return true
    }
}

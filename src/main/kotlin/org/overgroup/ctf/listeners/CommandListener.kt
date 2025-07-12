package org.overgroup.ctf.listeners

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.overgroup.ctf.commands.dropSheep
import org.overgroup.ctf.commands.sheepHat

class CommandListener : CommandExecutor {
    val commands = mapOf(
        "sheephat" to ::sheepHat,
        "dropsheep" to ::dropSheep,
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player ?: return false

        if (args.count() < 1) {
            player.sendMessage("Missing <subcommand> argument for command")
            return false
        }

        val subcommand = args.first()
        commands[subcommand]?.invoke(player) ?: {
            player.sendMessage("Unknown subcommand")
        }

        return true
    }
}
package org.overgroup.ctf.listeners

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.overgroup.ctf.commands.*

private val commands = mapOf(
    "sheephat" to ::sheepHat,
    "dropsheep" to ::dropSheep,
    "firewand" to ::fireWandItemCommand,
    "levwand" to ::levitationWandItemCommand,
)

class CommandListener : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player
        if (player == null) {
            sender.sendMessage("This command can only be executed by players")
            return false
        }

        if (args.count() < 1) {
            player.sendMessage("Missing <subcommand> argument for command")
            return false
        }

        val subcommand = args.first()
        val command = commands[subcommand]
        if (command == null) {
            sender.sendMessage("Unknown subcommand '$subcommand'")
            return false
        }

        command.invoke(player)

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String>? {
        if (args.size == 1) {
            return commands.keys.toList()
        }
        return emptyList()
    }
}

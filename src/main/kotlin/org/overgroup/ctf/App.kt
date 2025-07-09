package org.overgroup.ctf

import org.overgroup.ctf.commands.*
import org.overgroup.ctf.listeners.*
import org.bukkit.plugin.java.JavaPlugin

class App : JavaPlugin() {
    companion object {
        fun getInstance(): App {
            return getPlugin(App::class.java)
        }
    }

    override fun onEnable() {
        logger.info("Hello from Capture the Flag!")

        registerCommands()

        registerListeners()
    }

    override fun onDisable() {
    }

    private fun registerCommands() {
        getCommand("firewand")?.setExecutor(FireWandCommand())
        getCommand("sheephat")?.setExecutor(SheepHatCommand())
        getCommand("dropsheep")?.setExecutor(DropSheepCommand())
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(ItemUseListener(), this)
        server.pluginManager.registerEvents(PlayerFallDamageListener(), this)
        logger.info("Registered events")
    }
}

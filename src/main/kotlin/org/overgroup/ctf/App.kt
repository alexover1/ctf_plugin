package org.overgroup.ctf

import org.bukkit.plugin.java.JavaPlugin
import org.overgroup.ctf.listeners.*

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
        getCommand("ctf")?.setExecutor(CommandListener())
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(ItemUseListener(), this)
        server.pluginManager.registerEvents(PlayerFallDamageListener(), this)
        server.pluginManager.registerEvents(CustomWeaponHitListener(), this)

        logger.info("Registered events")
    }
}

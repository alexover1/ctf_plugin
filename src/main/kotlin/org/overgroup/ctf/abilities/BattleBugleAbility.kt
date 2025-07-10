package org.overgroup.ctf.abilities

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object BattleBugleAbility : SelfRegisteringAbility("Battle Bugle") {
    override fun use(player: Player, item: ItemStack) {
        val team = player.scoreboard.getEntityTeam(player)
        val entities = player.world.getNearbyLivingEntities(player.location, 15.0).filter {
            team?.hasEntity(it) ?: false
        }
        entities.forEach {
            it.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 300, 0, false, true))
            it.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 300, 0, false, true))
        }
        player.setCooldown(item, 200)
    }
}

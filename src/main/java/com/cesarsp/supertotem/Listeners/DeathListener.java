package com.cesarsp.supertotem.Listeners;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathListener implements Listener {
    @EventHandler
    void onPlayerDeathHurt(EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) entityDamageEvent.getEntity();
            PlayerInventory playerInv = player.getInventory();
            if (player.getHealth() - entityDamageEvent.getFinalDamage() <= 0
                    && playerInv.containsAtLeast(new ItemStack(Material.TOTEM_OF_UNDYING), 1)
            ) {
                //Getting the first stack that has a Totem of Undying and then subtracting one
                ItemStack firstSlotWithTotem = playerInv.all(Material.TOTEM_OF_UNDYING).values().iterator().next();
                firstSlotWithTotem.setAmount(0);
                //Totem animation & effects
                player.spawnParticle(Particle.TOTEM, player.getLocation(), 200);
                player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0F, 1.0F);
                player.playEffect(EntityEffect.TOTEM_RESURRECT);
                //player.playEffect(EntityEffect.LOVE_HEARTS);
                //Giving potion effects
                player.setHealth(4.0D);
                player.setFireTicks(0);
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 800, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 1));
                //TODO: Set advancement
                entityDamageEvent.setCancelled(true);
            }
        }
    }
}

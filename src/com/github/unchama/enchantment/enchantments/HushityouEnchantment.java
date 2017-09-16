package com.github.unchama.enchantment.enchantments;

import com.github.unchama.enchantment.GiganticEnchantment;
import com.github.unchama.event.PlayerDamageWithArmorEvent;
import com.github.unchama.gigantic.Gigantic;

import particles.ParticleEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.inventory.ItemStack;

/**
 * @author ga2ku
 */
public class HushityouEnchantment implements GiganticEnchantment {
	private List<String> coolDownPlayers = new ArrayList<>();
	private final static int COOL_DOWN = 60 * 20;
	private final static int MIN_DAMAGE = 10;
	private final static double HEALTH_REGEN = 20;
	private final static double PARTICLE = 30;
	
    @Override
    public boolean onEvent(Event event, Player player, ItemStack item, int level) {
        if (event instanceof PlayerDamageWithArmorEvent) {
        	EntityDamageEvent damageEvent = ((PlayerDamageWithArmorEvent) event).getOriginEvent();
        	if(damageEvent.isCancelled() || damageEvent.getDamage() < MIN_DAMAGE || coolDownPlayers.contains(player.getUniqueId().toString())){
        		return false;
        	}
    		double totalHealthRegen = HEALTH_REGEN - damageEvent.getDamage();
    		if(player.getHealth() + totalHealthRegen > player.getMaxHealth()){
    			totalHealthRegen = player.getMaxHealth() - player.getHealth();
    		}
    		EntityRegainHealthEvent regeinEvent = new EntityRegainHealthEvent(player, totalHealthRegen, RegainReason.CUSTOM);
    		Bukkit.getServer().getPluginManager().callEvent(regeinEvent);
    		if(regeinEvent.isCancelled()){
    			return false;
    		}
    		damageEvent.setDamage(0D);
    		player.setHealth(player.getHealth() + regeinEvent.getAmount());
    		playEffect(player);
    		coolDownPlayers.add(player.getUniqueId().toString());
    		Bukkit.getScheduler().runTaskLater(Gigantic.plugin, () -> coolDownPlayers.remove(player.getUniqueId().toString()), COOL_DOWN);
        }
        return false;
    }
    private void playEffect(Player player){
    	/*　Color付きのパーティクルについて
    	 * ・動作するのはparticles.ParticleEffectのdisplayメソッドだけでした。
    	 * ・displayメソッドのoffset,speed,amountは無効のようです。ParticleDataはnullでもなぜか行けました。
    	 * ・REDSTONE,SPELL_MOB,SPELL_MOB_AMBIENTはColorが付けれることを確認しました。
    	 * ・よくわからないけど下の方法だと色が付けれたので使いました(´・ω・｀)
    	 */
		Random ran = new Random();
		double x,y,z;
		for(double d = 0; d < PARTICLE; d++){
			x = ran.nextDouble() * 2 - 1;
			y = d / PARTICLE;
			z = ran.nextDouble() * 2 - 1;
			ParticleEffect.SPELL_MOB.display(null, player.getLocation().add(x, y, z), Color.fromRGB(255, (int)(255/PARTICLE * d), 0), 500, 0, 0, 0, 0, 0);
		}
		ParticleEffect.HEART.display(1F, 0.5F, 1F, 0, 4, player.getLocation().add(0, 1, 0), player.getWorld().getPlayers());

		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_BURN, 1, 0);
    }
}

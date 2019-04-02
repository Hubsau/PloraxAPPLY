package de.hubsau.ploraxapply.listener.impl;
/*Class erstellt von Hubsau


00:29 2019 31.03.2019
Wochentag : Sonntag


*/


import de.hubsau.ploraxapply.listener.AbstractListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LiveListener extends AbstractListener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent event){
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

}

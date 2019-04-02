package de.hubsau.ploraxapply.listener.impl;
/*Class erstellt von Hubsau


22:59 2019 29.03.2019
Wochentag : Freitag


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.listener.AbstractListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MoveListener extends AbstractListener {


    private Main main;

    public MoveListener(Main main) {
        this.main = main;
    }

    private List<Player> delay = new ArrayList<>();


    @EventHandler
    public void onMove(PlayerMoveEvent event){



        if(main.getApplyNPC() != null ) {
            Location npc = main.getApplyNPC().getLocation();

            Player player = event.getPlayer();

            if (player.getLocation().distance(npc) <= 3) {


                if (!delay.contains(player)) {
                    player.sendMessage(prefix() + "§7Ich habe viele Informationen §e(Rechtsklick)");

                    delay.add(player);
                    player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);





                }


            }else{
                if(delay.contains(player)){

                    delay.remove(player);

                }
            }
        }
    }


}

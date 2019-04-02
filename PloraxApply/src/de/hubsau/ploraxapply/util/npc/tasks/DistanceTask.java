package de.hubsau.ploraxapply.util.npc.tasks;
/*Class erstellt von Hubsau


14:51 2019 28.03.2019
Wochentag : Donnerstag


*/


import de.hubsau.ploraxapply.util.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class DistanceTask implements Runnable {

    public List<NPC> npcs;



    public DistanceTask(List<NPC> npcs) {
        this.npcs = npcs;

    }

    @Override
    public void run() {


            try {
                for (NPC npc : npcs) {
                    for (Player player : Bukkit.getOnlinePlayers()) {




                        if (npc.getLocation().getWorld().equals(player.getWorld())) {

                            if (npc.getLocation().distance(player.getLocation()) > 60 && npc.rendered.contains(player)) {


                                npc.destroy(player);


                            } else if (npc.getLocation().distance(player.getLocation()) < 60 && !npc.rendered.contains(player)) {


                                if (!npc.waiting.contains(player)) {
                                    npc.waiting.add(player);
                                    npc.destroy(player);
                                    npc.spawnEnttity(player);

                                }
                            }


                        } else {
                            npc.destroy(player);
                        }


                    }
                }


            }catch (Exception ex){


        }
    }
}

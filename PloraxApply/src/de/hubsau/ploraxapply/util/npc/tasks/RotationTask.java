package de.hubsau.ploraxapply.util.npc.tasks;
/*Class erstellt von Hubsau


15:54 2019 28.03.2019
Wochentag : Donnerstag


*/


import de.hubsau.ploraxapply.util.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class RotationTask implements Runnable {



    public List<NPC> npcs;



    public RotationTask(List<NPC> npcs) {
        this.npcs = npcs;

    }

    @Override
    public void run() {

        try {
            for (NPC nmsnpc : npcs) {

                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (nmsnpc.getLocation().getWorld().equals(pl.getWorld())) {



                        if (nmsnpc.getLocation().distance(pl.getLocation()) < 10) {

                            nmsnpc.headRotation(pl.getLocation().getYaw()-180, pl.getLocation().getPitch(), pl);

                        }

                    } else {
                        nmsnpc.destroy(pl);
                    }


                }
            }


        }catch (Exception ex){

        }



    }
}

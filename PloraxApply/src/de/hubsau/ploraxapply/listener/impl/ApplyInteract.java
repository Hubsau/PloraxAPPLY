package de.hubsau.ploraxapply.listener.impl;
/*Class erstellt von Hubsau


23:14 2019 29.03.2019
Wochentag : Freitag


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.listener.AbstractListener;
import de.hubsau.ploraxapply.util.npc.listener.Action;
import de.hubsau.ploraxapply.util.npc.listener.NPCInteractEvent;
import org.bukkit.event.EventHandler;

public class ApplyInteract extends AbstractListener {



    private Main main;

    public ApplyInteract(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onNPC(NPCInteractEvent event){

        if(event.getAction() == Action.RIGHT){

            if(event.getNPC().equals(main.getApplyNPC())) {

                main.getInventory().openAnimated(event.getPlayer());


            }
        }
    }

}

package de.hubsau.ploraxapply.util.npc.listener;
/*Class erstellt von Hubsau


13:56 2019 08.01.2019
Wochentag : Dienstag


*/


import de.hubsau.ploraxapply.util.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NPCInteractEvent extends Event implements Cancellable {


    private Player player;
    private NPC npc;
    public boolean cancelled = false;
    private Action action;

    private static final HandlerList handlers = new HandlerList();



    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }



    public NPCInteractEvent(Player player, NPC npc, Action action){
        this.player = player;
        this.npc = npc;
        this.action = action;

    }

    public Action getAction() {
        return action;
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNPC() {
        return npc;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = true;

    }
}

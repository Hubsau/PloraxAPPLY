package de.hubsau.ploraxapply.util.npc.listener;
/*Class erstellt von Hubsau


20:24 2019 08.01.2019
Wochentag : Dienstag


*/


import de.hubsau.ploraxapply.util.npc.util.PacketReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NPCListener implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        PacketReader reader = new PacketReader(event.getPlayer());
        reader.inject();
    }



}

package de.hubsau.ploraxapply.util.npc.util;
/*Class erstellt von Hubsau


14:09 2019 08.01.2019
Wochentag : Dienstag


*/




import de.hubsau.ploraxapply.util.npc.NPC;
import de.hubsau.ploraxapply.util.npc.listener.Action;
import de.hubsau.ploraxapply.util.npc.listener.NPCInteractEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;


public class PacketReader {

    Player player;
    Channel channel;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject(){
        CraftPlayer cPlayer = (CraftPlayer) this.player;
        channel = cPlayer.getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {@Override protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception {arg2.add(packet);readPacket(packet);}});
    }

    public void uninject(){
        if(channel.pipeline().get("PacketInjector") != null){
            channel.pipeline().remove("PacketInjector");
        }
    }


    public void readPacket(Packet<?> packet){
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")){

            String action = getValue(packet, "action").toString();

            if(action.equalsIgnoreCase("INTERACT") || action.equalsIgnoreCase("ATTACK")){
            int id = (Integer)getValue(packet, "a");
            for (NPC npc : NPC.getNpcs()) {

                if(npc.getEntityID() == id) {
                    if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {
                        NPCInteractEvent listener = new NPCInteractEvent(player, npc, Action.LEFT);
                        Bukkit.getPluginManager().callEvent(listener);

                    } else if (action.equalsIgnoreCase("INTERACT")) {
                        NPCInteractEvent listener = new NPCInteractEvent(player, npc, Action.RIGHT);

                        Bukkit.getPluginManager().callEvent(listener);


                    }
                }
                }
            }




        }
    }


    public void setValue(Object obj,String name,Object value){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }catch(Exception e){}
    }

    public Object getValue(Object obj,String name){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }catch(Exception e){}
        return null;
    }

}
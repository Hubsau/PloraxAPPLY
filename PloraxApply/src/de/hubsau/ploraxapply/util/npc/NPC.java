package de.hubsau.ploraxapply.util.npc;
/*Class erstellt von Hubsau


23:45 2019 04.01.2019
Wochentag : Freitag


*/


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.util.npc.tasks.DistanceTask;
import de.hubsau.ploraxapply.util.npc.tasks.ParticelTask;
import de.hubsau.ploraxapply.util.npc.tasks.RotationTask;
import de.hubsau.ploraxapply.util.npc.util.SkinData;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.*;


public class NPC {





    private static int id = 0;
    private PacketPlayOutScoreboardTeam scbpacket;
    private int entityID;
    private Location location;
    private GameProfile gameprofile;


    public List<Player> rendered = new ArrayList<Player>();
    public List<Player> waiting = new ArrayList<Player>();




    private static List<NPC> npcs = new ArrayList<NPC>();


    private static List<Player> npc = Main.getInstance().getPlayerNPC();


    private boolean showName;
    private String disPlayname;
    private static boolean taskstarted = false;



    public NPC(Location location, boolean showName){
        entityID = (int)Math.ceil(Math.random() * 1000) + 2000;
        this.location = location;
        this.showName = showName;

        if(!npcs.contains(this)){
            npcs.add(this);
        }
        showParticels(true, Main.getInstance());

    }


    public void showParticels(boolean show, Plugin plugin){

        if(show){

            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new ParticelTask(getLocation().clone()),  0, 0);

        }
    }

    public void delte(){
        if(npcs.contains(this)){
            npcs.remove(this);
        }

    }


    public static void startTask(Plugin plugin) {


        if (!taskstarted) {
            taskstarted = true;


            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new DistanceTask(npcs), 0, 20);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new RotationTask(npcs),  0, 2);



        }

    }

    public int getEntityID() {
        return entityID;
    }

    public void setGameProfile(String displayName, SkinData skindata)
    {
        this.disPlayname = displayName;
            GameProfile profile = new GameProfile(UUID.randomUUID(), displayName);
            profile.getProperties().put("textures", new Property("textures", skindata.getValue(), skindata.getSignature()));
            this.gameprofile = profile;

    }




    public void spawnEnttity(Player p){


        GameProfile profile = this.gameprofile;

       /*

        if(privateSkin.containsKey(p)){

            SkinData skinData  = privateSkin.get(p);
            profile = new GameProfile(UUID.randomUUID(), disPlayname);
            profile.getProperties().put("textures", new Property("textures", skinData.getValue(), skinData.getSignature()));
        }


        */


        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
        setValue(packet, "a", entityID);
        setValue(packet, "b", profile.getId());
        setValue(packet, "c", (int) MathHelper.floor(location.getX() * 32.0D));
        setValue(packet, "d", (int)MathHelper.floor(location.getY() * 32.0D));
        setValue(packet, "e", (int)MathHelper.floor(location.getZ() * 32.0D));
        setValue(packet, "f", (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        setValue(packet, "g", (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
        setValue(packet, "h", 0);
        DataWatcher w = new DataWatcher(null);
        w.a(10,(byte)127);
        w.a(3,"true");


        setValue(packet, "i", w);
        try {
            if(!showName) {
                //name anzeigen
                scbpacket = new PacketPlayOutScoreboardTeam();
                setValue(scbpacket, "h", 0);
                setValue(scbpacket, "b", profile.getName());
                setValue(scbpacket, "a", profile.getName());
                setValue(scbpacket, "e", "never");
                setValue(scbpacket, "i", 1);
                Field f = scbpacket.getClass().getDeclaredField("g");
                f.setAccessible(true);
                ((Collection) f.get(scbpacket)).add(profile.getName());
                sendPacket(scbpacket, p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

/*

        if(privateSkin.containsKey(p)){
            addToTablist(p, profile);

        }else
 */


            addToTablist(p);
        sendPacket(packet, p);
        PacketPlayOutEntityHeadRotation rotationpacket = new PacketPlayOutEntityHeadRotation();
        setValue(rotationpacket, "a", entityID);
        setValue(rotationpacket, "b", (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        sendPacket(rotationpacket, p);
        GameProfile finalProfile = profile;
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable(){
            @Override
            public void run() {


               /*

                if(privateSkin.containsKey(p)){

                    rmvFromTablist(p, finalProfile);

                }
                */
                rmvFromTablist(p);

            }
        },26);
        this.rendered.add(p);
        this.waiting.remove(p);


        if(!npcs.contains(this))
            npcs.add(this);
    }


    public void headRotation(float yaw,float pitch, Player player){
        PacketPlayOutEntity.PacketPlayOutEntityLook packet = new PacketPlayOutEntity.PacketPlayOutEntityLook(entityID, getFixRotation(yaw),getFixRotation(pitch) , true);
        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
        setValue(packetHead, "a", entityID);
        setValue(packetHead, "b", getFixRotation(yaw));


        sendPacket(packet, player);
        sendPacket(packetHead, player);
    }

    private int getFixLocation(double pos){
        return (int)MathHelper.floor(pos * 32.0D);
    }

    private byte getFixRotation(float yawpitch){
        return (byte) ((int) (yawpitch * 256.0F / 360.0F));
    }

    public void destroy(Player p){
        GameProfile profile = this.gameprofile;
        try{


            rmvFromTablist(p);

            PacketPlayOutScoreboardTeam removescbpacket = new PacketPlayOutScoreboardTeam();
            Field f = removescbpacket.getClass().getDeclaredField("a");
            f.setAccessible(true);
            f.set(removescbpacket, profile.getName());
            f.setAccessible(false);
            Field f2 = removescbpacket.getClass().getDeclaredField("h");
            f2.setAccessible(true);
            f2.set(removescbpacket, 1);
            f2.setAccessible(false);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(removescbpacket);

            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] {entityID});
            sendPacket(packet, p);

            this.rendered.remove(p);
            npc.remove(this);



        }catch(Exception ex){
        }
    }


    private void addToTablist(Player p){
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet, p);
    }

    private void addToTablist(Player p, GameProfile profile){
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(profile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(profile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet, p);
    }

    public Location getLocation() {
        return location;
    }

    private void rmvFromTablist(Player p){
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameprofile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet, p);
    }

    private void rmvFromTablist(Player p, GameProfile profile){
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(profile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(profile.getName())[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet, p);
    }


    private void setValue(Object obj,String name,Object value){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }catch(Exception e){}
    }

    private Object getValue(Object obj,String name){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }catch(Exception e){}
        return null;
    }

    public String getDisPlayname() {
        return disPlayname;
    }

    private void sendPacket(Packet<?> packet, Player player){
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }


    public static List<NPC> getNpcs() {
        return npcs;
    }
}

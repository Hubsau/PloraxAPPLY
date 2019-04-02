package de.hubsau.ploraxapply.file;
/*Class erstellt von Hubsau


19:58 2019 15.01.2019
Wochentag : Dienstag


*/


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigLocationFactory {

    FileConfiguration cfg;

    public ConfigLocationFactory(FileConfiguration cfg){this.cfg = cfg;}


    /*

    BLOCK

     */
    public void createCfgBlock(Location loc, String path, File file) {
        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getBlockX());
        cfg.set(path + ".Y", loc.getBlockY());
        cfg.set(path + ".Z", loc.getBlockZ());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Location getCfgBlock(String path) {
        World w = Bukkit.getWorld(cfg.getString(path + ".World"));
        int x = cfg.getInt(path + ".X");
        int y = cfg.getInt(path + ".Y");
        int z = cfg.getInt(path + ".Z");
        return new Location(w, x, y, z);

    }
    /*

    Location ohne YawPitch

     */



    public void createCfg(Location loc, String path, File file) {
        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".Y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCfg(YamlConfiguration cfg, Location loc, String path, File file) {
        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".Y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Location getCfg(String path)   {
        World w = Bukkit.getWorld(cfg.getString(path + ".World"));
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".Y");
        double z = cfg.getDouble(path + ".Z");

        return new Location(w, x, y, z);

    }

    /*

    Location mit YawPitch

     */


    public void createCfgYawPitch(Location loc, String path, File file) {
        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".Y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());
        cfg.set(path + ".Yaw", loc.getYaw());
        cfg.set(path + ".Pitch", loc.getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCfgYawPitch(YamlConfiguration cfg, Location loc, String path, File file) {
        this.cfg = cfg;
        cfg.set(path + ".World", loc.getWorld().getName());
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".Y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());
        cfg.set(path + ".Yaw", loc.getYaw());
        cfg.set(path + ".Pitch", loc.getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Location getCfgYP(String path) {
        World w = Bukkit.getWorld(cfg.getString(path + ".World"));
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".Y");
        double z = cfg.getDouble(path + ".Z");
        float yaw = (float) cfg.getDouble(path + ".Yaw");
        float pitch = (float) cfg.getDouble(path + ".Pitch");
        return new Location(w, x, y, z, yaw, pitch);

    }
    public Location getCfgYPconfig(String path, FileConfiguration cfg) {

        Location re = null;


        this.cfg = cfg;
        World w = Bukkit.getWorld(cfg.getString(path + ".World"));
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".Y");
        double z = cfg.getDouble(path + ".Z");
        float yaw = (float) cfg.getDouble(path + ".Yaw");
        float pitch = (float) cfg.getDouble(path + ".Pitch");
        re=  new Location(w, x, y, z, yaw, pitch);


        return re;

    }


}

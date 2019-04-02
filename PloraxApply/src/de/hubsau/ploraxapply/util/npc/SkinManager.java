package de.hubsau.ploraxapply.util.npc;
/*Class erstellt von Hubsau


00:06 2019 05.01.2019
Wochentag : Samstag


*/


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.hubsau.ploraxapply.util.npc.util.Callback;
import de.hubsau.ploraxapply.util.npc.util.SkinData;

import de.hubsau.ploraxapply.util.npc.util.SkinDataReply;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SkinManager {

    private static SkinData data;

    private static JsonObject getJsonResponse(String url){
        URL ipAdress;
        JsonObject rootobj = null;
        try {
            ipAdress = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));
            String jsonresponse = in.readLine();
            JsonParser jsonParser = new JsonParser();
            JsonElement root = jsonParser.parse(jsonresponse);
            rootobj = root.getAsJsonObject();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return rootobj;
    }

    public static void getUUIDFromName(final Plugin plugin, String name, Callback<String> callback){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            public void run() {
                long unixTime = System.currentTimeMillis() / 1000L;
                JsonObject jsonresponse = getJsonResponse("https://api.mojang.com/users/profiles/minecraft/"+name+"?at="+unixTime);
                if(jsonresponse!=null && jsonresponse.get("error")==null){
                    callback.call(jsonresponse.get("id").getAsString());
                }else{
                    callback.call(null);
                }
            }
        });
    }

    public static SkinData getSkinFromMojangAsync(final Plugin plugin, final String name, final SkinDataReply skinreply){
        data = null;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new BukkitRunnable() {
            public void run() {


                try {
                    URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                    InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
                    String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();

                    URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
                    InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
                    JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
                    String value = textureProperty.get("value").getAsString();
                    String signature = textureProperty.get("signature").getAsString();


                    skinreply.done(new SkinData(value, signature));
                } catch (IOException e) {
                    System.err.println("Could not get skin data from session servers!");
                }


            }
        });

        return data;
    }








    public static SkinData getSkinFromMineskinAsync(final Plugin plugin, final int mineskinid){
        data = null;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            public void run() {
                JsonObject jsonresponse = getJsonResponse("https://api.mineskin.org/get/id/"+mineskinid);
                if(jsonresponse!=null && !jsonresponse.has("error")){
                    JsonObject textureProperty = jsonresponse.getAsJsonObject("data").getAsJsonObject("texture");
                    String value = textureProperty.get("value").getAsString();
                    String signature = textureProperty.get("signature").getAsString();
                    data = new SkinData(value, signature);
                }else{
                    data = null;
                }
            }
        });
        return data;

    }

    public static void getString(final Plugin plugin, final String value, final String signature, final SkinDataReply skinreply){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            public void run() {

                skinreply.done(new SkinData(value, signature));

            }
        });
    }


}

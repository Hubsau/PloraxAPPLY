package de.hubsau.ploraxapply.listener.impl;
/*Class erstellt von Hubsau


20:43 2019 30.03.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.listener.AbstractListener;
import de.hubsau.ploraxapply.util.scoreboard.Scoreboard;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class JoinListener extends AbstractListener {

    private Main main;
    private HashMap<String, Integer> scores;

    private Scoreboard scoreboard;




    public JoinListener(Main main) {





        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String birthDay = "07.11.2003";

        Date datum = null;
        try {
            datum = df.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        long ms = today.getTime()-datum.getTime();
        long sec= ms/1000;
        long min = sec/60;
        long hur = min/60;
        long dys = hur/24;
        long years = dys/365;




        this.main = main;
        this.scores = new HashMap<>();
        scores.put("§7§1", 10);
        scores.put("§8"+coreMessage.DOUBLE_ARROW +"§7Name", 9);
        scores.put("§8  "+coreMessage.ARROW +"§6Anton", 8);

        scores.put("§7§2", 7);
        scores.put("§8"+coreMessage.DOUBLE_ARROW +"§7Alter", 6);
        scores.put("§8  "+coreMessage.ARROW +"§e"+years, 5);
        scores.put("§7§3", 4);
        scores.put("§8"+coreMessage.DOUBLE_ARROW +"§7Entwicklungsumgebung", 3);
        scores.put("§8  "+coreMessage.ARROW +"§eIntelliJ", 2);
        scores.put("§7§4", 1);



    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        scoreboard = new Scoreboard(player);


        scoreboard.setSideBar(scores, "§7§l➜ "+prefix().replaceAll(" §8\\x2a §r", ""));
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.setMaxHealth(5);
        player.setHealth(5);
        player.setFoodLevel(20);
        player.setNoDamageTicks(99999999);

    }


}

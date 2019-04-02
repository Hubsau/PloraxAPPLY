package de.hubsau.ploraxapply.util.scoreboard;
/*Class erstellt von Hubsau


21:30 2018 01.08.2018
Wochentag : Mittwoch


*/


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class Scoreboard {

    Player player;
    org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();


    public Scoreboard(Player player) {
        this.player = player;
    }


    public void setSideBar(HashMap<String , Integer> scores, String title){

        Objective obj = board.getObjective(player.getName());
        if(obj != null){
            obj.unregister();
        }
        obj = board.registerNewObjective(player.getName(), "dummy"+player.getName());
        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (String score : scores.keySet())

            obj.getScore(score).setScore(scores.get(score));
        player.setScoreboard(board);
    }
    public void update(String prefix, String suffix, String entry){
        board = player.getScoreboard();
        Team team = board.getTeam(entry+1);
        if(team == null) {
            team = board.registerNewTeam(entry + 1);
        }
            team.addEntry(entry);
            team.setPrefix(prefix);
            team.setSuffix(suffix);

    }

    public String getScoreSuffix(String entry){
        String score = "";
        board = player.getScoreboard();
        Team team = board.getTeam(entry+1);
        if(team != null){
            score = team.getSuffix();
        }
        return score;
    }
    public String getScorePrefix(String entry){
        String score = "";
        board = player.getScoreboard();
        Team team = board.getTeam(entry+1);
        if(team != null){
            score = team.getPrefix();
        }
        return score;
    }


}

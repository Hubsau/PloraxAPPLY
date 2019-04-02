package de.hubsau.ploraxapply.command.impl;
/*Class erstellt von Hubsau


21:53 2019 27.03.2019
Wochentag : Mittwoch


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.command.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ApplyCommand extends AbstractCommand {



    private Main main;



    public ApplyCommand(Main main) {

        super("setbewerbung", Arrays.asList(""), true);
        this.main  = main;
    }

    @Override
    public void sendCommand(CommandSender sender, String[] args) {


        Player player = (Player)sender;

        if(player.hasPermission("apply.plorax")){


            main.getConfigurationFiles().getConfigFile().setNPCSpawn(player.getLocation());

            player.sendMessage(prefix()+"§7Der Spawn-Punkt für das §6Entity §7wurde gesetzt");
            main.loadNPC();

        }else{
            player.sendMessage(coreMessage.NO_PERMISSION);
        }




    }
}

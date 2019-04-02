package de.hubsau.ploraxapply.inventory.impl;
/*Class erstellt von Hubsau


20:31 2019 30.03.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.listener.AbstractListener;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ClickListener extends AbstractListener {

    private Main main;

    int i;


    public ClickListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){


        try{

            event.setCancelled(true);
            ApplyInventory inventory = main.getInventory();

            ItemStack current = event.getCurrentItem();

            if(current.equals(inventory.getPlaceholder())|| current.getType().equals(Material.DIAMOND)){

                return;

            }else{

                Player player = (Player) event.getWhoClicked();

                if(current.equals(inventory.getBook1())){

                    player.closeInventory();

                    List<String> whoAmI = main.getConfigurationFiles().getConfigFile().getWhoAmI();



                    startReading(player, whoAmI);


                }else {
                    if (current.equals(inventory.getBook2())) {


                        player.closeInventory();

                        List<String> whyI = main.getConfigurationFiles().getConfigFile().getWhyI();


                        startReading(player, whyI);

                    }else{
                        player.closeInventory();

                        List<String> code = main.getConfigurationFiles().getConfigFile().getCode();


                        startReading(player, code);
                    }
                }

            }

        }catch (NullPointerException e){


        }

    }
    private void startReading(Player player, List<String> info){

        this.i = 0;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(i < info.size()) {
                    player.sendMessage("§7" + info.get(i).replace("&", "§"));
                    player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1, 1);


                    i++;

                }else{
                    player.sendMessage(prefix()+"§7Da war's klicke noch einmal auf mich um mehr über Hubsau | Anton zu erfahren.");
                    timer.cancel();
                }

            }

        }, 200, 8000);


    }

    @EventHandler
    public void onItem(PlayerItemHeldEvent event){
        event.setCancelled(true);
    }


}

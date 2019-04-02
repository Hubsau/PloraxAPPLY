package de.hubsau.ploraxapply;
/*Class erstellt von Hubsau


21:50 2019 27.03.2019
Wochentag : Mittwoch


*/


import de.hubsau.ploraxapply.command.impl.ApplyCommand;
import de.hubsau.ploraxapply.file.ConfigLocationFactory;
import de.hubsau.ploraxapply.file.ConfigurationFiles;
import de.hubsau.ploraxapply.file.impl.ConfigFile;
import de.hubsau.ploraxapply.inventory.impl.ApplyInventory;
import de.hubsau.ploraxapply.inventory.impl.ClickListener;
import de.hubsau.ploraxapply.listener.impl.ApplyInteract;
import de.hubsau.ploraxapply.listener.impl.JoinListener;
import de.hubsau.ploraxapply.listener.impl.LiveListener;
import de.hubsau.ploraxapply.listener.impl.MoveListener;
import de.hubsau.ploraxapply.setup.Setup;
import de.hubsau.ploraxapply.util.npc.NPC;
import de.hubsau.ploraxapply.util.npc.listener.NPCListener;
import de.hubsau.ploraxapply.util.npc.util.SkinData;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {


    private static Main instance;

    public List<Player> playerNPC;

    private ConfigurationFiles configurationFiles;
    private ConfigLocationFactory factory;

    private NPC applyNPC;
    private ApplyInventory inventory;

    @Override
    public void onEnable() {

        instance = this;

        intialize();
    }

    private void intialize(){


        this.configurationFiles = new ConfigurationFiles();

        this.factory = new ConfigLocationFactory(getConfigurationFiles().getConfigFile().getConfiguration());

        NPC.startTask(this);

        playerNPC = new ArrayList<>();

        Setup setup = new Setup(this, "ploraxapply");

        setup.addListener(new NPCListener());
        setup.addListener(new MoveListener(this));
        setup.addListener(new ApplyInteract(this));
        setup.addCommand(new ApplyCommand(this));
        setup.addListener(new ClickListener(this));
        setup.addListener(new JoinListener(this));
        setup.addListener(new LiveListener());
        setup.register();



        loadNPC();
        loadInventory();




    }
    private void loadInventory(){

        inventory = new ApplyInventory();
        inventory.fillPlaceHolder();
        inventory.fill();

    }

    public ApplyInventory getInventory() {
        return inventory;
    }

    public void loadNPC(){


        ConfigFile file = getConfigurationFiles().getConfigFile();
        try{
            if(file.getNPCSpawn() != null){

                if(applyNPC != null){

                    applyNPC.delte();
                }

                applyNPC = new NPC(file.getNPCSpawn(), true);

                applyNPC.setGameProfile("§7➜ §e§LHubsau", new SkinData("eyJ0aW1lc3RhbXAiOj" +
                        "E1NTM4OTYwMzM0ODgsInByb2ZpbGVJZCI6IjdjZGRhZTBiY2EyYzRlNzlhYjI1MjhjMmI4NDB" +
                        "lMzJlIiwicHJvZmlsZU5hbWUiOiJUZXh0bWFya2VyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRyd" +
                        "WUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5u" +
                        "ZXQvdGV4dHVyZS80NzQ5NGE2NTc2MmE2YmQxYmYzOGY1ODMwY2JmOWMzZDFiYzA5" +
                        "OGY1MjNhYzg1YmFmNGU2MDBlMjE0ZDJm" +
                        "MjAzIn19fQ==",
                        "Q8pz/cdkBpe7NPaI6kjmiOxWuZv3Y" +
                                "RjyZwTOawVV4FF+EQmdgBD9/CsNfJ5iI" +
                                "hHgVVIAK40S2AZnK71+khsPPGZHPg4evu" +
                                "Mea2I4Hi0PRMf/cEWKsomIiDFWtbuIVVap" +
                                "Fq/GDB24mVxVSPWh1Z9nOl1sXC5a3LUDkq" +
                                "QcsqDxtSc7XDRrXrruQJNiofa67eVjWaavh" +
                                "vOsku0N0lr4aMDAClboRkDcQpn0u7V2nm6TV" +
                                "VgODzTPKyJOXMoXCDJ3HZBuq+EGpoMgV1kzQ" +
                                "t0fDrFexKQLozhfYa2iN5TEMaINdUw/57Pyh" +
                                "Dlaezs+buUYv4BxRebupBACLVb4IOFZLtGL5" +
                                "6xDKwuy9T2NONYF/kSX97OUZSFLkl5U/5I95zo" +
                                "qk2IXhXfHCkLxpPWmce86Mtvn0CcOquciTu+qy3" +
                                "ZGJIRV052j5gHPluxPlBOnGU7WgTPjY3LuLTLBK" +
                                "vatmxEDse81+Rd4NHVIaLn4ey3YPQZB6GBvXPAB" +
                                "ZB3Hcflp/enCBZPHI4Dtdy/3ijXnKidQaWnQYa6" +
                                "thV7wUZFB6a6ksdZThgpSvE" +
                                "BGJzWDw5FuCbKj1hyHn1nGoWP6em5SsqLYVLjFj" +
                                "p4kPZYRsr3Ko+B4bRpQ5/pM+rFR5TLXVH8DB8PvkR" +
                                "P/U1fHnzj1EdvdTqodRzyYmv0dhfQNZvRQ/L+M7MtellQ="));
               /*

                SkinManager.getSkinFromMojangAsync(this, "Textmarker", new SkinDataReply() {
                    @Override
                    public void done(SkinData skinData) {

                        applyNPC.setGameProfile("Textmarker", skinData);
                    }
                });

                */


            }

        }catch (IllegalArgumentException e){


        }

    }


    public static Main getInstance() {
        return instance;
    }

    public List<Player> getPlayerNPC() {
        return playerNPC;
    }
    public ConfigurationFiles getConfigurationFiles() {
        return configurationFiles;
    }

    public NPC getApplyNPC() {
        return applyNPC;
    }

    public ConfigLocationFactory getFactory() {
        return factory;
    }
}

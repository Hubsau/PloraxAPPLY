package de.hubsau.ploraxapply.file;
/*Class erstellt von Hubsau


19:28 2019 15.01.2019
Wochentag : Dienstag


*/


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public abstract class AbstractFile {


    private File file;
    private FileConfiguration configuration;
    private Plugin plugin;

    public void setFile(File file)
    {

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.file = file;
    }

    public void setConfiguration(FileConfiguration configuration)
    {
        this.configuration = configuration;
    }

    public void setPlugin(Plugin plugin)
    {
        this.plugin = plugin;
    }

    public File getFile()
    {
        return this.file;
    }

    public FileConfiguration getConfiguration()
    {
        return this.configuration;
    }

    public Plugin getPlugin()
    {
        return this.plugin;
    }

    public AbstractFile(Plugin plugin, String fileName)
    {

        setPlugin(plugin);
        setFile(new File(getPlugin().getDataFolder(), fileName + ".yml"));
        setConfiguration(YamlConfiguration.loadConfiguration(getFile()));
        write();
    }

    private void write()
    {
        if (!getPlugin().getDataFolder().exists()) {
            getPlugin().getDataFolder().mkdirs();
        }
        if (!getFile().exists())
        {
            try
            {

                getFile().createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


            create();
            save();
        }
    }

    public void save()
    {
        try
        {
            getConfiguration().save(getFile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void create();

}

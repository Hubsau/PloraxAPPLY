package de.hubsau.ploraxapply.file;
/*Class erstellt von Hubsau


19:29 2019 15.01.2019
Wochentag : Dienstag


*/


import de.hubsau.ploraxapply.file.impl.ConfigFile;

public class ConfigurationFiles {


    private ConfigFile configFile;



    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }


    public ConfigFile getConfigFile() {
        return configFile;
    }

    public ConfigurationFiles()
    {


        setConfigFile(new ConfigFile());

    }

}

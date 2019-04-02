package de.hubsau.ploraxapply.file.impl;
/*Class erstellt von Hubsau


21:14 2019 30.03.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxapply.Main;
import de.hubsau.ploraxapply.file.AbstractFile;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ConfigFile extends AbstractFile {


    public void setNPCSpawn(Location location){

        Main.getInstance().getFactory().createCfgYawPitch(location, "NPCspawn", getFile());
        save();

    }
    public Location getNPCSpawn(){
        return Main.getInstance().getFactory().getCfgYPconfig("NPCspawn", getConfiguration());

    }


    public ConfigFile() {
        super(Main.getInstance(), "config");


    }
    public List<String> getWhoAmI(){
        return getConfiguration().getStringList("whoAmI");
    }

    public List<String> getWhyI(){
        return getConfiguration().getStringList("whyI");
    }

    public List<String> getCode(){
        return getConfiguration().getStringList("code");
    }



    @Override
    public void create() {
        List<String> whoAmI = new ArrayList();
        whoAmI.add("Liebe &6&lPlorax&e&LNet &7Serverleitung");
        whoAmI.add("da ich bereits vor einem Jahr &bDeveloper &7bei euch w" +
                "ar, ich mich aber wegen &n\"Multiteaming\"&r&7 beim Team verabschieden musste, beschloss ich mich erneut bei euch zu Bewerben.");


        whoAmI.add("Ich zögerte" +
                " also nicht lange un" +
                "d fragte den &4Projekt" +
                "leiter Perry007_&7, nach einer Entschuldigung wegen " +
                "meines Fehlverhaltens beim Teamkick, ob ihr mich " +
                "erneut gebrauchen könnt.");


        whoAmI.add("Dieser zögerte nicht lange und bat mir dieses Plugin als Testaufgabe zu programmieren.");

        whoAmI.add("Aber kommen wir nun zu meiner Person");

        whoAmI.add("Derzeit bin ich 15 Jahre alt und besuche die neunte Klasse einer Realschule in Bayern.");

        whoAmI.add("Bis vor kurzen leitete ich s" +
                "elbst, zusammen mi" +
                "t einem Bekannten, ein Proje" +
                "kt, welches sich aber wegen de" +
                "r Inaktivität des Projektleiters, (der Bekannte) auflöste.");


        whoAmI.add("Das zusammenarbeiten im Team bereitet mir viel Freude und auch selbständiges Arbeiten zählt zu meinen Stärken.");
        whoAmI.add("Des Weiteren bin ich immer bereit Neues zu lernen und anderen meine Erfahrung zu Teilen.");
        whoAmI.add("Außerdem interessiere ich mich sehr für Computer und beschäftige mich auch viel mit der " +
                "Informatik, was eines Tages auch dazu beigetragen hat Java zu lernen.");
        whoAmI.add("In meiner Freizeit beschäftige ich mich neben meinen Rechner auch oft" +
                " draußen, deswegen zählt Fahrradfahren ebenfalls zu meinen Hobbys.");


        getConfiguration().set("whoAmI", whoAmI);

        List<String> whyI = new ArrayList();

        whyI.add("Sicherlich stellt sich die Frage, warum genau ihr mich nehmen sollt.");
        whyI.add("Ich werde euch bei der Weiterentwicklung des Netzwerkes sehr weiterhelfen" +
                " können, da ich so gut wie jede Aufgabe in meinen Bereichen zuverlässig und" +
                " ordentlich bewältigt bekomme.");
        whyI.add("Was sich auch in den letzten Jahren, vor allem aber Monaten, sehr an meiner " +
                "Entwicklung (Programmierkenntnisse | dazu kommen wir später) zeigt.");
        whyI.add("Außerdem konnte ich innerhalb von &azwei &7Jahren &lviel &7Erfahrung mit Minecraft " +
                "Servern und deren &6Community &7sammeln, was euch im viele Bereiche ebenfalls sehr " +
                "weiterbringen wird &8(z.B. Benutzerfreundlichkeit der Systeme).");
        whyI.add("Da es im Team sehr wichtig ist mit Kritik umzugehen, lernte ich auch den Umgang mit Beschwerden, " +
                "sei es &bTeam-Intern &7oder von einem User außerhalb des Teams, was mich dabei half, mich in der " +
                "Programmierung positiv weiterzubilden.");
        whyI.add("Ein weiters Argument, ist meines &lGutes Verständnis " +
                "&7für Informatik. Beispielsweise fällt es mir &lsehr leicht " +
                "&7neu Dinge am PC zu lernen und diese gegebenenfalls " +
                "anderen beizubringen, da ich mich gut in die Situation anderer hineinversetzen kann.");
        whyI.add("Zu guter Letzt stellt ihr mich mit " +
                "Plorax.NET einer neuen Herausforderung.\n" +
                "Ich bin mir sicher, neues bei euch zu lernen " +
                "und dadurch meine Kenntnisse in allen Bereichen " +
                "des &bDevelopers &7zu verbessern und zu vergrößern.\n");

        getConfiguration().set("whyI", whyI);


        List<String> code = new ArrayList();


        code.add("Kommen wir zu meinen Programmierkenntnissen");
        code.add("In Laufe der Zeit (vor 2 Jahren) lernte ich sehr viel über die Programmiersprache Java.");
        code.add("Ich beschäftigte mich mit den Java Conventions und der Objektorientierten Programmierung.");
        code.add("Des Weiteren begann ich mich mit der &6Bukkit/Spigot &7und &eBungeeCord API &7sehr gut auseinanderzusetzen.");
        code.add("Kenntnisse mit den Datenbanken &9&lMySQL, " +
                "&a&lMongoDB &7und &4&lRedis &7gehören auch zu meiner " +
                "Person, außerdem benutzte ich das ein oder andere Mal &6&lMaven&7, mit dem ich ebenfalls gut zurecht komme.");
        code.add("Fremde API’s eigne ich mir sehr schnell an, deswegen" +
                " gehören auch die &9&lDiscord JDA &7und &9&lTeamspeak³" +
                " API &7zu denen die ich schon das ein" +
                " oder andere Mal benutzte.");
        code.add("Zu guter Letzt kenne ich mich ein wenig mit der Sprache &dC# &7aus.");
        code.add("Ich hoffe, ich konnte euch überzeugen mich erneut in das Team aufzunehmen" +
                " und würde mich sehr über eine Einladung zu ein Gespräch Freuen.\n" +
                "Mit freundlichen Grüßen\n");
        code.add("&6&lAnton Hubsau");

        getConfiguration().set("code", code);



    }
}

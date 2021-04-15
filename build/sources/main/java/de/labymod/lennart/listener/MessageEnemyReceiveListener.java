package de.labymod.lennart.listener;


import de.labymod.lennart.Main;
import net.labymod.api.events.MessageReceiveEvent;

public class MessageEnemyReceiveListener implements MessageReceiveEvent {

    public static String enemy = null;
    public static String kit = null;
    public static String latestMap = null;

    @Override
    public boolean onReceive(String s, String strippedMessage) {

        if (strippedMessage.contains("Mapvoting") && strippedMessage.contains("»") && strippedMessage.contains("beendet")) {
            String[] mapname = s.split("§6");
            String mapoutput = mapname[mapname.length-1];
            mapoutput = mapoutput.substring(0,mapoutput.length()-5);
            latestMap = mapoutput;

            if (Main.INSTANCE.mapAnswer) {
                Main.INSTANCE.mapAnswer = false;
                return false;
            }
        }

        if (strippedMessage.contains("1vs1") && strippedMessage.contains("»")) {

            if (strippedMessage.contains("Kit") && strippedMessage.contains("Einstellungen")) {

                String kitname = s.split("§6")[1].split("§8")[0];
                kitname = kitname.substring(0, kitname.length()-1);
                kit = kitname;

            }

            if (strippedMessage.contains("Kampf") && strippedMessage.contains("beginnt")) {

                String enemyname = s.split("§6")[1].split("§7")[0];
                enemyname = enemyname.substring(0, enemyname.length()-2);
                enemy = enemyname;

                if (enemy.contains("&") && enemy.contains(" ")) {
                    return false;
                }
            }

            else if (strippedMessage.contains("Du") && strippedMessage.contains("hast den Kampf gegen")) {
                enemy = null;
                kit = null;
            }
        } return false;
    }
}
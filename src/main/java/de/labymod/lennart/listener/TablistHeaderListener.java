package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.TabListEvent;

public class TablistHeaderListener implements TabListEvent {
    @Override
    public void onUpdate(Type type, String s, String s1) {
        if (s.contains("Du spielst auf")) {
            String[] servername = s.split("§6");
            String serveroutput = servername[servername.length-1];
            serveroutput = serveroutput.substring(0,serveroutput.length()-4);
            TimoliaAddon.INSTANCE.latestserver = serveroutput;
        }
    }
}
package com.github.l3nnartt.timolia.listener;

import com.github.l3nnartt.timolia.TimoliaAddon;
import com.github.l3nnartt.timolia.modules.ServerSupport;
import net.labymod.api.events.TabListEvent;
import net.minecraft.client.Minecraft;
import java.util.List;

public class TablistHeaderListener implements TabListEvent {

    @Override
    public void onUpdate(Type type, String header, String s1) {
        if (header.contains("Du spielst auf")) {
            String[] servername = header.split("§6");
            String serveroutput = servername[servername.length-1];
            serveroutput = serveroutput.substring(0,serveroutput.length()-4);
            TimoliaAddon.instance.latestServer = serveroutput;
        }

        if (containsTimoliaServer(header)) {
            Minecraft.getMinecraft().player.sendChatMessage("/v");
            TimoliaAddon.getInstance().setMapAnswer(true);
        } ServerSupport.latestMap = null;
    }

    private boolean containsTimoliaServer(String gamemode, String s) {
        return s.contains("§r§7 Du spielst auf §r§6§o" + gamemode);
    }

    private boolean containsTimoliaServer(String s) {
        List<String> serverList = TimoliaAddon.getInstance().getAddonConfig().getServers();
        for (String server : serverList) {
            if (containsTimoliaServer(server, s)) {
                return true;
            }
        } return false;
    }
}
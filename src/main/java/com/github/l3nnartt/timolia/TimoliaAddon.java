package com.github.l3nnartt.timolia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.l3nnartt.timolia.config.ServerConfig;
import com.github.l3nnartt.timolia.listener.MessageEnemyReceiveListener;
import com.github.l3nnartt.timolia.listener.TablistHeaderListener;
import com.github.l3nnartt.timolia.modules.ServerSupport;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import java.util.List;

public class TimoliaAddon extends LabyModAddon {

    public static TimoliaAddon instance;
    public String latestServer;
    public boolean mapAnswer;
    public Gson gson;
    public ServerConfig addonConfig;

    @Override
    public void onEnable() {
        instance = this;
        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = ServerConfig.read();

        // Listener
        api.getEventManager().register(new TablistHeaderListener());
        api.getEventManager().register(new MessageEnemyReceiveListener());

        // Modules
        api.registerServerSupport(this, new ServerSupport());

        // start debug
        getLogger("Addon successful activated");
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    public static void getLogger(String logMessage) {
        System.out.println("[Timolia] " + logMessage);
    }

    public static TimoliaAddon getInstance() {
        return instance;
    }

    public String getLatestServer() {
        return latestServer;
    }

    public void setLatestServer(String latestServer) {
        this.latestServer = latestServer;
    }

    public boolean isMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(boolean mapAnswer) {
        this.mapAnswer = mapAnswer;
    }

    public ServerConfig getAddonConfig() {
        return addonConfig;
    }
}
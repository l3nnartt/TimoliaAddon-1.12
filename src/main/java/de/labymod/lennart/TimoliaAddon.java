package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import de.labymod.lennart.listener.TablistHeaderListener;
import de.labymod.lennart.modules.ServerSupport;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import java.util.List;

public class TimoliaAddon extends LabyModAddon {

    public static TimoliaAddon INSTANCE;
    public String latestserver = null;
    public boolean mapAnswer = false;
    public String[] servers;
    public Gson gson;
    public AddonConfig addonConfig;

    @Override
    public void onEnable() {
        INSTANCE = this;
        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = AddonConfig.read();

        //Enable Message
        System.out.println("Timolia-Addon enabled!");

        //Listener
        api.getEventManager().register(new TablistHeaderListener());
        api.getEventManager().register(new MessageEnemyReceiveListener());

        //Modules
        api.registerServerSupport(this, new ServerSupport());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    public static TimoliaAddon getINSTANCE() {
        return INSTANCE;
    }

    public String getLatestserver() {
        return latestserver;
    }

    public void setLatestserver(String latestserver) {
        this.latestserver = latestserver;
    }

    public boolean isMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(boolean mapAnswer) {
        this.mapAnswer = mapAnswer;
    }

    public AddonConfig getAddonConfig() {
        return addonConfig;
    }
}
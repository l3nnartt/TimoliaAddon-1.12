package com.github.l3nnartt.timolia;

import com.github.l3nnartt.timolia.karmatop.Authenticator;
import com.github.l3nnartt.timolia.karmatop.KarmaListener;
import com.github.l3nnartt.timolia.karmatop.KarmaUpdater;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.l3nnartt.timolia.config.ServerConfig;
import com.github.l3nnartt.timolia.listener.MessageEnemyReceiveListener;
import com.github.l3nnartt.timolia.listener.TablistHeaderListener;
import com.github.l3nnartt.timolia.modules.ServerSupport;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimoliaAddon extends LabyModAddon {

    public static TimoliaAddon instance;
    public String latestServer;
    public boolean mapAnswer;
    public Gson gson;
    public ServerConfig addonConfig;

    // Authenticator
    Authenticator authenticator;
    private final ExecutorService exService = Executors.newSingleThreadExecutor();

    // Karmatop
    private boolean karmaAnswer;
    private boolean enabledKarmaUpdater;

    @Override
    public void onEnable() {
        instance = this;

        // Authenticator
        getLogger("auth");
        authenticator = new Authenticator();

        // Karmatop
        getLogger("karmalistener");
        api.getEventManager().register(new KarmaListener());
        getLogger("karmaupdater");
        api.getEventManager().registerOnJoin(new KarmaUpdater());

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
        this.enabledKarmaUpdater = !getConfig().has("enabledKarmaUpdater") || getConfig().get("enabledKarmaUpdater").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> subSettings) {
        subSettings.add(new HeaderElement("Allgemein"));
        subSettings.add(new BooleanElement("KarmaUpdater", this, new ControlElement.IconData(Material.EXP_BOTTLE), "enabledKarmaUpdater", this.enabledKarmaUpdater));
    }

    public ExecutorService getExService() {
        return exService;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public boolean isKarmaAnswer() {
        return karmaAnswer;
    }

    public void setKarmaAnswer(boolean karmaAnswer) {
        this.karmaAnswer = karmaAnswer;
    }

    public boolean isEnabledKarmaUpdater() {
        return enabledKarmaUpdater;
    }

    public static void getLogger(String logMessage) {
        System.out.println("[TimoliaAddon] " + logMessage);
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
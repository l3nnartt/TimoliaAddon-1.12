package de.labymod.lennart;

import com.google.gson.Gson;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import de.labymod.lennart.listener.TablistHeaderListener;
import de.labymod.lennart.modules.ServerSupport;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.util.ResourceLocation;
import java.util.List;

public class TimoliaAddon extends LabyModAddon {

    public static TimoliaAddon INSTANCE;
    public ModuleCategory timolia;
    public String latestserver = null;
    public boolean mapAnswer = false;
    public String[] servers;
    public Gson gson;
    public AddonConfig addonConfig;

    @Override
    public void onEnable() {
        INSTANCE = this;

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

    public static void setINSTANCE(TimoliaAddon INSTANCE) {
        TimoliaAddon.INSTANCE = INSTANCE;
    }

    public ModuleCategory getTimolia() {
        return timolia;
    }

    public void setTimolia(ModuleCategory timolia) {
        this.timolia = timolia;
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

    public String[] getServers() {
        return servers;
    }

    public void setServers(String[] servers) {
        this.servers = servers;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public AddonConfig getAddonConfig() {
        return addonConfig;
    }

    public void setAddonConfig(AddonConfig addonConfig) {
        this.addonConfig = addonConfig;
    }
}
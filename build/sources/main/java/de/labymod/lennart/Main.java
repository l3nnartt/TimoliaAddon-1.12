package de.labymod.lennart;

import com.google.gson.Gson;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import de.labymod.lennart.listener.TablistHeaderListener;
import de.labymod.lennart.modules.Enemy;
import de.labymod.lennart.modules.Kit;
import de.labymod.lennart.modules.Server;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class Main extends LabyModAddon {

    public static Main INSTANCE;
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

        //GUI Entry
        timolia = new ModuleCategory("Timolia", true, new ControlElement.IconData(new ResourceLocation("icons/timolia/timolia128.png")));
        System.out.println("Timolia-Addon enabled");
        ModuleCategoryRegistry.loadCategory(timolia);

        //Listener
        this.getApi().getEventManager().register(new TablistHeaderListener());
        this.getApi().getEventManager().register(new MessageEnemyReceiveListener());

        //Modules
        this.getApi().registerModule(new Server());
        this.getApi().registerModule(new Enemy());
        this.getApi().registerModule(new Kit());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
}
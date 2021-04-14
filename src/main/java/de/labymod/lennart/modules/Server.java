package de.labymod.lennart.modules;

import de.labymod.lennart.Main;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class Server extends SimpleModule {

    @Override
    public ModuleCategory getCategory() {
        return Main.INSTANCE.timolia;
    }

    @Override
    public String getDisplayName() {
        return "Server";
    }

    @Override
    public String getDisplayValue() {
        return Main.INSTANCE.latestserver;
    }

    @Override
    public String getDefaultValue() {
        return "???";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("icons/timolia/enemy128.png"));
    }

    @Override
    public void loadSettings() {
    }

    @Override
    public String getSettingName() {
        return "Lobby/Server";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Zeigt dir die aktuelle Lobby/Server";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
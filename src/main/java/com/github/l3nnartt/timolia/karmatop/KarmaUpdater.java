package com.github.l3nnartt.timolia.karmatop;

import com.github.l3nnartt.timolia.TimoliaAddon;
import net.labymod.utils.ServerData;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

public class KarmaUpdater implements Consumer<ServerData>, net.labymod.utils.Consumer<ServerData> {

    @Override
    public void accept(ServerData serverData) {
        TimoliaAddon.getLogger("accept server data");
        if (TimoliaAddon.getInstance().isEnabledKarmaUpdater()) {
            if(serverData.getIp().toLowerCase().contains("timolia.de")) {
                TimoliaAddon.getInstance().setKarmaAnswer(true);
                TimoliaAddon.getLogger("consumer hoert auf karma");
                Minecraft.getMinecraft().player.sendChatMessage("/karma");
            }
        }
    }

    @Override
    public Consumer<ServerData> andThen(Consumer<? super ServerData> after) {
        return Consumer.super.andThen(after);
    }
}
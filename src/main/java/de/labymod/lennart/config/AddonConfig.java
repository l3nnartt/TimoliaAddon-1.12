package de.labymod.lennart.config;

import com.google.common.io.Resources;
import de.labymod.lennart.TimoliaAddon;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AddonConfig {

    private final List<String> servers = new ArrayList<>();
    private AddonConfig(){};

    public static AddonConfig read() {
        URL url = Resources.getResource("servers.json");
        String content = null;
        try {
            content = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TimoliaAddon.INSTANCE.gson.fromJson(content, AddonConfig.class);
    }

    public List<String> getServers() {
        return servers;
    }
}
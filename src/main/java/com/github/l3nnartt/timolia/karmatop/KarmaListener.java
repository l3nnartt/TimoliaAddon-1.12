package com.github.l3nnartt.timolia.karmatop;

import com.github.l3nnartt.timolia.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;

import java.net.HttpURLConnection;
import java.net.URL;

public class KarmaListener implements MessageReceiveEvent {

    private String karma = null;

    @Override
    public boolean onReceive(String s, String strippedMessage) {
        TimoliaAddon.getLogger("hoere auf karma");
        if (TimoliaAddon.getInstance().isEnabledKarmaUpdater()) {
            TimoliaAddon.getLogger("enabled karmaupdater");
            TimoliaAddon.getLogger("s" + s);
            TimoliaAddon.getLogger("strippedMessage" + strippedMessage);
            if (s.contains("Deine Erfolgspunkte-Informationen")) return true;
            TimoliaAddon.getLogger("erstes if");
            if (s.contains("Erfolgspunkte") && s.contains("│") && s.contains(":")) {
                TimoliaAddon.getLogger("zweites if");
                if (s.contains("-")) return false;
                TimoliaAddon.getLogger("drittes if");
                String[] karmaPunkte = s.split("§6");
                String karmaoutput = karmaPunkte[karmaPunkte.length - 1];
                karmaoutput = karmaoutput.substring(0, karmaoutput.length() - 2);
                karma = karmaoutput;
                TimoliaAddon.getLogger("karma" + karma);

                if (TimoliaAddon.getInstance().getAuthenticator().authenticate()) {
                    TimoliaAddon.getLogger("auth");
                    TimoliaAddon.getInstance().getExService().execute(() -> {
                        TimoliaAddon.getLogger("try");
                        try {
                            HttpURLConnection con = (HttpURLConnection) (new URL("http://dl.lennartloesche.de/karmatop/auth.php?name=" + LabyMod.getInstance().getLabyModAPI().getPlayerUsername() + "&karma=" + karma + "&uuid=" + LabyMod.getInstance().getLabyModAPI().getPlayerUUID())).openConnection();
                            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                            con.connect();
                            int code = con.getResponseCode();
                            if (code == 200) {
                                TimoliaAddon.getLogger("Karma gesendet");
                            } else {
                                TimoliaAddon.getLogger("FEHLERCODE:" + code);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }

                if (TimoliaAddon.getInstance().isKarmaAnswer()) {
                    TimoliaAddon.getInstance().setKarmaAnswer(false);
                    TimoliaAddon.getLogger("höre nicht mehr");
                    return true;
                }
            }
        } return false;
    }
}
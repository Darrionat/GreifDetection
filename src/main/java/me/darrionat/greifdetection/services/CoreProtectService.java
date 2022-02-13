package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.GreifDetection;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CoreProtectService {
    private final GreifDetection plugin;
    private final PlaytimeService playtimeService;
    private final CoreProtectAPI api;

    public CoreProtectService(GreifDetection plugin, PlaytimeService playtimeService) {
        this.plugin = plugin;
        this.api = initCoreProtect();
        this.playtimeService = playtimeService;
    }

    private CoreProtectAPI initCoreProtect() {
        Plugin cp = plugin.getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (cp == null || !(cp instanceof CoreProtect)) {
            return null;
        }
        // Check that the API is enabled
        CoreProtectAPI api = ((CoreProtect) cp).getAPI();
        if (!api.isEnabled()) {
            return null;
        }
        // Check that a compatible version of the API is loaded
        if (api.APIVersion() < 7) {
            return null;
        }
        return api;
    }

    public boolean enabled() {
        return api != null;
    }

    public CoreProtectAPI getCoreProtectAPI() {
        return api;
    }

    public void analyzePlayer(OfflinePlayer player) {
        List<String> players = new ArrayList<>();
        players.add(player.getName());
        long accountAge = playtimeService.getAccountAge(player.getUniqueId());
        List<String[]> result = api.performLookup((int) (accountAge / 1000), players, null, null, null, null, 0, null);
    }
}
package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.GreifDetection;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.plugin.Plugin;

public class CoreProtectService {
    private final GreifDetection plugin;
    private final CoreProtectAPI api;

    public CoreProtectService(GreifDetection plugin) {
        this.plugin = plugin;
        this.api = initCoreProtect();
    }

    private CoreProtectAPI initCoreProtect() {
        Plugin cp = plugin.getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (cp == null || !(cp instanceof CoreProtect)) {
            return null;
        }
        // Check that the API is enabled
        CoreProtectAPI api = ((CoreProtect) cp).getAPI();
        if (api.isEnabled() == false) {
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
}
package me.darrionat.greifdetection.listeners;

import me.darrionat.greifdetection.GreifDetection;
import me.darrionat.greifdetection.services.CoreProtectService;
import me.darrionat.greifdetection.services.FlagService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final CoreProtectService coreProtect;
    private final FlagService flagService;

    public BlockBreakListener(GreifDetection plugin, FlagService flagService, CoreProtectService coreProtectService) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.flagService = flagService;
        this.coreProtect = coreProtectService;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!flagService.runChecks(p))
            return;
        // coreProtect.analyzePlayer
    }
}

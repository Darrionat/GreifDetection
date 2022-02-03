package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.flags.FlagType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FlagService {
    private final PlaytimeService playtimeService;

    public FlagService(PlaytimeService playtimeService) {
        this.playtimeService = playtimeService;
    }

    public boolean runChecks(Player p) {

        return true;
    }

    // todo more information on flags
    public void flagPlayer(Player p, Location loc, FlagType flagType) {

    }
}
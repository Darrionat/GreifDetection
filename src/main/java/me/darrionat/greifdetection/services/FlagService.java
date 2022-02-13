package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.repositories.AnalyzedPlayersRepository;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FlagService {
    private final AnalyzedPlayersRepository analyzedPlayersRepo;

    public FlagService(AnalyzedPlayersRepository analyzedPlayersRepo) {
        this.analyzedPlayersRepo = analyzedPlayersRepo;
    }

    public boolean runChecks(Player p) {
        if (analyzedPlayersRepo.contains(p.getUniqueId()))
            return false;
        // todo other stuff
        return true;
    }

    // todo more information on flags
    private void flagPlayer(Player p, Location loc, String reason) {

    }
}
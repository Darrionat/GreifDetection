package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.GreifDetection;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlaytimeService {
    private final HashMap<UUID, LogInfo> logData = new HashMap<>();

    public PlaytimeService(GreifDetection plugin) {
        OfflinePlayer[] players = plugin.getServer().getOfflinePlayers();
        for (OfflinePlayer p : players) {
            logData.put(p.getUniqueId(), new LogInfo(p.getFirstPlayed(), p.getLastPlayed()));
        }
    }

    static class LogInfo {
        private final long firstLog, lastLog;

        LogInfo(long firstLog, long lastLog) {
            this.firstLog = firstLog;
            this.lastLog = lastLog;
        }

        public long getFirstLog() {
            return firstLog;
        }

        public long getLastLog() {
            return lastLog;
        }
    }

    /**
     * Checks to see if a player has joined recently within a given time frame.
     * <p>
     * For example: If {@code timeFrame=5000} and the player joined 4 seconds ago, return true.
     *
     * @param p         The player to check
     * @param timeFrame The amount of time to check within, in milliseconds
     * @return {@code true} if the player has joined within the given timeframe; {@code false} otherwise.
     */
    public boolean recentFirstJoin(Player p, long timeFrame) {
        long firstJoin = logData.get(p.getUniqueId()).getFirstLog();
        return System.currentTimeMillis() - timeFrame < firstJoin;
    }
}
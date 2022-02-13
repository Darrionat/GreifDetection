package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.GreifDetection;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;

public class PlaytimeService {
    private final HashMap<UUID, LogInfo> logData = new HashMap<>();

    public PlaytimeService(GreifDetection plugin) {
        OfflinePlayer[] players = plugin.getServer().getOfflinePlayers();
        for (OfflinePlayer p : players) {
            UUID uuid = p.getUniqueId();
            logData.put(uuid, new LogInfo(uuid, p.getFirstPlayed(), p.getLastPlayed()));
        }
    }

    static class LogInfo implements Comparable<LogInfo> {
        private final UUID uuid;
        private final long firstLog, lastLog;

        LogInfo(UUID uuid, long firstLog, long lastLog) {
            this.uuid = uuid;
            this.firstLog = firstLog;
            this.lastLog = lastLog;
        }

        public UUID getUuid() {
            return uuid;
        }

        public long getFirstLog() {
            return firstLog;
        }

        public long getLastLog() {
            return lastLog;
        }

        @Override
        public int compareTo(@NotNull PlaytimeService.LogInfo b) {
            long diff = lastLog - firstLog;
            long diff2 = b.lastLog - b.firstLog;
            return (int) (diff - diff2);
        }
    }

    private long getFirstLog(UUID uuid) {
        return logData.get(uuid).getFirstLog();
    }

    private long getLastLog(UUID uuid) {
        return logData.get(uuid).getLastLog();
    }

    /**
     * Checks to see if a player has joined recently within a given time frame.
     * <p>
     * For example: If {@code timeFrame=5000} and the player joined 4 seconds ago, return true.
     *
     * @param uuid      The uuid of the player to check
     * @param timeFrame The amount of time to check within, in milliseconds
     * @return {@code true} if the player has joined within the given timeframe; {@code false} otherwise.
     */
    public boolean recentFirstJoin(UUID uuid, long timeFrame) {
        long firstJoin = getFirstLog(uuid);
        return System.currentTimeMillis() - timeFrame < firstJoin;
    }

    /**
     * Gets the amount of time in ms since the given player's first join.
     *
     * @param uuid The uuid of the player to check
     * @return milliseconds since first log. {@code -1} if the player has never joined.
     */
    public long getAccountAge(UUID uuid) {
        return System.currentTimeMillis() - getFirstLog(uuid);
    }

    public PriorityQueue<LogInfo> getPriorityQueue() {
        PriorityQueue<LogInfo> queue = new PriorityQueue<>();
        queue.addAll(logData.values());
        return queue;
    }
}
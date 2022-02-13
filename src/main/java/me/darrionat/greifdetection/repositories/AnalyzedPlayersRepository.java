package me.darrionat.greifdetection.repositories;

import me.darrionat.greifdetection.GreifDetection;
import me.darrionat.pluginlib.files.Config;
import me.darrionat.pluginlib.files.ConfigBuilder;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a file that stores players who have been analyzed. Players that are within this file will be skipped when
 * analyzing.
 */
public class AnalyzedPlayersRepository {
    private final Config file;
    private final FileConfiguration config;

    public AnalyzedPlayersRepository(GreifDetection plugin) {
        ConfigBuilder builder = new ConfigBuilder(plugin, "analyzedPlayers.yml");
        file = builder.build();
        config = file.getFileConfiguration();
    }

    /**
     * Gets a HashMap of all players who have been analyzed.
     *
     * @return A HashMap of all players who have been analyzed. Keys are the UUID and values are their last log.
     */
    public HashMap<UUID, Long> getPlayers() {
        HashMap<UUID, Long> toReturn = new HashMap<>();
        for (String uuid : config.getKeys(false)) {
            toReturn.put(UUID.fromString(uuid), config.getLong(uuid));
        }
        return toReturn;
    }

    public boolean contains(UUID uuid) {
        return config.getConfigurationSection(uuid.toString()) == null;
    }

    public void addPlayer(UUID uuid, long lastLog) {
        config.set(uuid.toString(), lastLog);
        file.save(config);
    }

    public void removePlayer(UUID uuid) {
        config.set(uuid.toString(), null);
        file.save(config);
    }
}
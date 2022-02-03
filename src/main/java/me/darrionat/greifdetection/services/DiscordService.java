package me.darrionat.greifdetection.services;

import me.darrionat.greifdetection.repositories.ConfigRepository;

public class DiscordService {
    private final ConfigRepository config;

    public DiscordService(ConfigRepository config) {
        this.config = config;
    }

    public void sendFlag() {

    }
}
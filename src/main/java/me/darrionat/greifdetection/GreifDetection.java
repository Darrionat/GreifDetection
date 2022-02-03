package me.darrionat.greifdetection;

import me.darrionat.pluginlib.ErrorHandler;
import me.darrionat.pluginlib.Plugin;

public class GreifDetection extends Plugin {
    @Override
    public void initPlugin() {
// take into account the age of the build
        // randomly check recently new players and their block removals
        // always watch new players and their block removals
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }
}
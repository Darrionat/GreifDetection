package me.darrionat.greifdetection.statics;

public class Bootstrapper {
    private static Bootstrapper instance;

    private Bootstrapper() {

    }

    public static Bootstrapper getInstance() {
        if (instance == null)
            instance = new Bootstrapper();
        return instance;
    }
}

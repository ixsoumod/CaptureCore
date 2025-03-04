package fr.ixsou.capturecore;

import org.bukkit.plugin.java.JavaPlugin;

public final class CaptureCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CaptureCore is now enabled !");

    }

    @Override
    public void onDisable() {
        getLogger().info("CaptureCore is now disabled !");
    }
}

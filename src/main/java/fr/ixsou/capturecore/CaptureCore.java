package fr.ixsou.capturecore;

import fr.ixsou.capturecore.commands.CaptureCoreAdmin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CaptureCore extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("capturecore").setExecutor(new CaptureCoreAdmin(this));
        getLogger().info("CaptureCore is now enabled !");
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        getLogger().info("CaptureCore is now disabled !");
    }

    public String getHelpMessage() {
        return "§bMessage d'aide";
    }

}

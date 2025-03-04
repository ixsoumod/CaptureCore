package fr.ixsou.capturecore;

import fr.ixsou.capturecore.commands.CaptureCoreAdmin;
import net.md_5.bungee.api.ChatColor;
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

    public String getMessage(String path) {
        if (path.equals("no-found-message")) {
            return ChatColor.translateAlternateColorCodes('&', "&cMessage introuvable !");
        }

        Object messageObject = getConfig().get("messages." + path);

        if (messageObject instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', (String) messageObject);
        }

        return getMessage("no-found-message");
    }

}

package fr.ixsou.capturecore.commands;

import fr.ixsou.capturecore.CaptureCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CaptureCoreAdminEdit implements CommandExecutor {

    private final CaptureCore plugin;

    public CaptureCoreAdminEdit(CaptureCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("edit")) {
            if (!sender.hasPermission("capturecore.admin")) {
                sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§cUtilisation: /capturecore edit <nom>");
                return true;
            }

            String nomArene = args[1];

            if (plugin.getConfig().get("arenes." + nomArene) == null) {
                sender.sendMessage("§cL'arène " + nomArene + " n'existe pas !");
                return true;
            }
            return true;
        }


        return true;
    }
}

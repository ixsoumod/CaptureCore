package fr.ixsou.capturecore.commands;

import fr.ixsou.capturecore.CaptureCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CaptureCoreAdmin implements CommandExecutor {

    private final CaptureCore plugin;

    public CaptureCoreAdmin(CaptureCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /capturecore help");
            return true;
        }

        // Commande d'aide
        if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("capturecore.admin")) {
                sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                return true;
            }
            sender.sendMessage(plugin.getHelpMessage());
            return true;
        }

        // Commande de reload
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("capturecore.admin")) {
                sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                return true;
            }
            plugin.reloadConfig();
            sender.sendMessage("§aConfiguration rechargée !");
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!sender.hasPermission("capturecore.admin")) {
                sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§cUtilisation: /capturecore create <nom>");
                return true;
            }

            String nomArene = args[1];

            // Vérifier si l'arène existe déjà dans la config
            if (plugin.getConfig().getConfigurationSection("arenes." + nomArene) != null) {
                sender.sendMessage("§cL'arène " + nomArene + " existe déjà !");
                return true;
            }

            // Ajouter l'arène dans la config avec juste son nom
            plugin.getConfig().set("arenes." + nomArene + ".name", nomArene);
            plugin.saveConfig();

            sender.sendMessage("§aArène ajoutée: " + nomArene);
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (!sender.hasPermission("capturecore.admin")) {
                sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§cUtilisation: /capturecore delete <nom>");
                return true;
            }

            String nomArene = args[1];

            if (plugin.getConfig().getConfigurationSection("arenes." + nomArene) == null) {
                sender.sendMessage("§cL'arène " + nomArene + " n'existe pas !");
                return true;
            }

            plugin.getConfig().set("arenes." + nomArene, null);
            plugin.saveConfig();

            sender.sendMessage("§aArène supprimée: " + nomArene);
            return true;
        }



        sender.sendMessage("§cCommande inconnue ! Fait /capturecore help pour voir les commandes disponibles.");
        return true;
    }
}

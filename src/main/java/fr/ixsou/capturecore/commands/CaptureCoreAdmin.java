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
    private final List<String> Arene = new ArrayList<>();

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
        if (args[0].equalsIgnoreCase("help") && sender.hasPermission("capturecore.admin")) {
            sender.sendMessage(plugin.getHelpMessage());
            return true;
        }else {
            sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
        }

        // Commande de reload
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("capturecore.admin")) {
            plugin.reloadConfig();
            sender.sendMessage("§aConfiguration rechargée !");
            return true;
        } else {
            sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
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

            String nomarene = args[1]; // Récupérer l'argument 1
            Arene.add(nomarene); // Ajouter à la liste
            plugin.getConfig().set("arenas", nomarene); // Sauvegarder la liste dans la configuratio


            plugin.saveConfig(); // Sauvegarder la configuration

            sender.sendMessage("§aArène créer: " + nomarene);
            return true;
        }

        sender.sendMessage("§cCommande inconnue ! Fait /capturecore help pour voir les commandes disponibles.");
        return true;
    }
}

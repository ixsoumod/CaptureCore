package fr.ixsou.capturecore.utils;

import fr.ixsou.capturecore.CaptureCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TabComplete implements TabCompleter {

    private final CaptureCore plugin;

    public TabComplete(CaptureCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(Arrays.asList("reload", "help", "create", "delete", "join", "leave", "start", "stop", "edit"));
        }
        else if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
            // Vérifie si "arenes" existe dans la config
            if (plugin.getConfig().getConfigurationSection("arenes") != null) {
                Set<String> arenes = plugin.getConfig().getConfigurationSection("arenes").getKeys(false);
                completions.addAll(arenes);
            }
        }
        else if (args.length == 3 && args[0].equalsIgnoreCase("edit")) {
            // Vérifie si l'arène spécifiée existe avant de proposer les options d'édition
            if (plugin.getConfig().getConfigurationSection("arenes") != null) {
                Set<String> arenes = plugin.getConfig().getConfigurationSection("arenes").getKeys(false);
                if (arenes.contains(args[1])) {
                    completions.addAll(Arrays.asList("name", "maxplayers", "minplayers", "waitinglobby", "lobby", "spectator", "spawn"));
                }
            }
        }
        else if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
            completions.add("<nom de ta nouvelle arène>");
        }
        else if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
            if (plugin.getConfig().getConfigurationSection("arenes") != null) {
                completions.addAll(plugin.getConfig().getConfigurationSection("arenes").getKeys(false));
            }
        }

        return completions;
    }
}

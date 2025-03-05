package fr.ixsou.capturecore.commands;

import fr.ixsou.capturecore.CaptureCore;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CaptureCoreAdminEdit implements CommandExecutor {

    private final CaptureCore plugin;

    public CaptureCoreAdminEdit(CaptureCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        Location lobby = player.getLocation();
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

            if (args.length < 3) {
                sender.sendMessage("§cUtilisation: /capturecore edit <nom> <action>");
                return true;
            }

            if (args[2].equalsIgnoreCase("setlobby")) {
                plugin.getConfig().set("arenes." + nomArene + ".lobby.world", lobby.getWorld().getName());
                plugin.getConfig().set("arenes." + nomArene + ".lobby.x", lobby.getX());
                plugin.getConfig().set("arenes." + nomArene + ".lobby.y", lobby.getY());
                plugin.getConfig().set("arenes." + nomArene + ".lobby.z", lobby.getZ());
                plugin.getConfig().set("arenes." + nomArene + ".lobby.yaw", lobby.getYaw());
                plugin.getConfig().set("arenes." + nomArene + ".lobby.pitch", lobby.getPitch());

                plugin.saveConfig();

                sender.sendMessage("§aLe lobby de l'arène " + nomArene + " a été défini sur en " + lobby.getX() + ", " + lobby.getY() + ", " + lobby.getZ());
                return true;
            }
            return true;
        }


        return true;
    }

    public void teleportToLobby(@NotNull CommandSender sender, String nomArene) {
        Player player = (Player) sender;
        if (plugin.getConfig().get("arenes." + nomArene + ".lobby") == null) {
            sender.sendMessage("§cLe lobby de l'arène " + nomArene + " n'est pas défini !");
            return;
        }

        String world = plugin.getConfig().getString("arenes." + nomArene + ".lobby.world");
        double x = plugin.getConfig().getDouble("arenes." + nomArene + ".lobby.x");
        double y = plugin.getConfig().getDouble("arenes." + nomArene + ".lobby.y");
        double z = plugin.getConfig().getDouble("arenes." + nomArene + ".lobby.z");
        float yaw = (float) plugin.getConfig().getDouble("arenes." + nomArene + ".lobby.yaw");
        float pitch = (float) plugin.getConfig().getDouble("arenes." + nomArene + ".lobby.pitch");

        Location lobby = new Location(player.getServer().getWorld(world), x, y, z, yaw, pitch);
        player.teleport(lobby);
        sender.sendMessage("§aVous avez été téléporté au lobby de l'arène " + nomArene);
    }
}

package com.batesy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public class CreeperGriefCommand implements CommandExecutor, TabCompleter {

    private final BatesPlugin plugin;
    private String[] inpArgs = { "true", "false" };

    public CreeperGriefCommand(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (args.length == 0) {
                sender.sendMessage("§fCreeper Griefing is currently set to: " + plugin.getConfig().getBoolean("creeper-grief"));
            return true;
        }

        if (args.length != 1 || !Arrays.asList(inpArgs).contains(args[0])) {
            sender.sendMessage("§cUsage: /creepergrief true|false");
            return true;
        }

        if (args[0].equalsIgnoreCase("true")) {
            plugin.getConfig().set("creeper-grief", true);
        }

        if (args[0].equalsIgnoreCase("false")) {
            plugin.getConfig().set("creeper-grief", false);
        }

        plugin.saveConfig();
        sender.sendMessage("§fCreeper Griefing is now set to: " + plugin.getConfig().getBoolean("creeper-grief"));

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(inpArgs);
        }
        return Collections.emptyList();

    }
}

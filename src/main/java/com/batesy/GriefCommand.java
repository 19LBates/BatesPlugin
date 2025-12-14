package com.batesy;

import java.util.Collections;
import java.util.List;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public class GriefCommand implements CommandExecutor, TabCompleter {

    private final BatesPlugin plugin;
    private final List<String> mobs = List.of("creeper", "wither", "ghast", "enderman") ;
    private static final List<String> bools = List.of("true", "false");

    public GriefCommand(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            sendCurrentState(sender, command, label, args);
            return true;
        }

        if (args.length == 2) {
            changeCurrentState(sender, command, label, args);
            return true;
        }

        sender.sendMessage(plugin.mm().deserialize("<red>Usage: <usage></red>", Placeholder.parsed("usage", command.getUsage())));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            return mobs.stream().filter(m -> m.startsWith(args[0].toLowerCase())).toList();
        }

        if (args.length == 2) {
            return bools.stream().filter(b -> b.startsWith(args[1].toLowerCase())).toList();
        }

        return Collections.emptyList();
    }

    private void sendCurrentState(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!mobs.contains(args[0])) {
            sender.sendMessage(plugin.mm().deserialize("<red>Usage: <usage></red>", Placeholder.parsed("usage", command.getUsage())));
            return;
        }

        sender.sendMessage(plugin.mm().deserialize("<white><mob> Griefing is currently set to:</white> <state>",
                Placeholder.parsed("state", String.valueOf(plugin.getConfig().getBoolean("grief." + args[0]))),
                Placeholder.parsed("mob", firstCap(args[0]))));

    }

    private void changeCurrentState(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!mobs.contains(args[0]) || !bools.contains(args[1])) {
            sender.sendMessage(plugin.mm().deserialize("<red>Usage: <usage></red>", Placeholder.parsed("usage", command.getUsage())));
            return;
        }

        boolean enabled = Boolean.parseBoolean(args[1]);
        plugin.getConfig().set("grief." + args[0], enabled);
        plugin.saveConfig();
        sender.sendMessage(plugin.mm().deserialize("<white><mob> Griefing now currently set to:</white> <state>",
                Placeholder.parsed("state", String.valueOf(enabled)),
                Placeholder.parsed("mob", firstCap(args[0]))));

    }

    private String firstCap(String s) {
        if (s == null || s.isBlank()) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}

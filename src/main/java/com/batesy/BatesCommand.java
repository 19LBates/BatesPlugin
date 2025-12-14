package com.batesy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class BatesCommand implements CommandExecutor, TabCompleter {
    private final BatesPlugin plugin;
    private final List<String> subcommands = List.of("reload", "version");

    public BatesCommand(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(plugin.mm().deserialize("<gradient:#41FF6D:#72BAFF>[BatesPlugin]</gradient> <white>Welcome to BatesPlugin!</white>"));
            return true;
        }

        if (args.length != 1 || !subcommands.contains(args[0])) {
            sender.sendMessage(plugin.mm().deserialize("<red>Usage: /bates reload</red>"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(plugin.mm().deserialize("<gradient:#41FF6D:#72BAFF>[BatesPlugin]</gradient> <white>Config reloaded!</white>"));
        }

        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(plugin.mm().deserialize("<gradient:#41FF6D:#72BAFF>[BatesPlugin]</gradient> <white>You are running on version <version>!</white>", plugin.pluginPlaceholders()));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            return subcommands.stream().filter(s -> s.startsWith(args[0].toLowerCase())).toList();
        }

        return Collections.emptyList();
    }
}

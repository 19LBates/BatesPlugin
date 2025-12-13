package com.batesy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BatesCommand implements CommandExecutor, TabCompleter {
    private final BatesPlugin plugin;
    private final MiniMessage mm = MiniMessage.miniMessage();
    private String[] inpArgs = { "reload" };

    public BatesCommand(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(mm.deserialize("<gradient:#41FF6D:#72BAFF>Welcome to the BatesPlugin!</gradient>"));
            return true;
        }

        if (args.length != 1 || !Arrays.asList(inpArgs).contains(args[0])) {
            sender.sendMessage(mm.deserialize("<red>Usage: /bates reload</red>"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(mm.deserialize("<gradient:#41FF6D:#72BAFF>[BatesPlugin]</gradient> <white>Config reloaded!</white>"));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(inpArgs);
        }
        return Collections.emptyList();
    }
}

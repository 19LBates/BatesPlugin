package com.batesy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class SkibCommand implements CommandExecutor {

    private final BatesPlugin plugin;

    public SkibCommand(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        sender.sendMessage(plugin.mm().deserialize(
                "<gradient:#ff0000:#ffff00>Skibidi Rizz from Ohio!</gradient>"));
        return true;
    }
}

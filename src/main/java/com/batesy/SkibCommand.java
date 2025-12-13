package com.batesy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class SkibCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {

        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:#ff0000:#ffff00>Skibidi Rizz from Ohio!</gradient>"));
        return true;
    }
}

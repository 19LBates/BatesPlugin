package com.batesy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class SkibCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:#ff0000:#ffff00>Skibidi Rizz from Ohio!</gradient>"));
        return true;
    }
}

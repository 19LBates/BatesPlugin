package com.batesy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.minimessage.MiniMessage;

public class SkibCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        player.sendMessage(MiniMessage.miniMessage().deserialize(
        "<gradient:#ff0000:#ffff00>Skibidi Rizz from Ohio!</gradient>"
    ));

        return true;
    }
}

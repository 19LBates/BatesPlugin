package com.batesy;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreeperGriefCommand implements CommandExecutor {

    private String[] allowedArgs = { "on", "off" };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (BatesPlugin.getInstance().getConfig().getBoolean("creeper-grief")) {
                sender.sendMessage("§fCreeper Griefing is currently §aON");
            } else {
                sender.sendMessage("§fCreeper Griefing is currently §cOFF");
            }
            return true;
        }

        if (args.length != 1 || !Arrays.asList(allowedArgs).contains(args[0])) {
            sender.sendMessage("§cUsage: /creepergrief on|off");
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {
            BatesPlugin.getInstance().getConfig().set("creeper-grief", true);
            BatesPlugin.getInstance().saveConfig();
            sender.sendMessage("§fCreeper Griefing turned §aON");
            sender.sendMessage("§eKaboom!");
            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {
            BatesPlugin.getInstance().getConfig().set("creeper-grief", false);
            BatesPlugin.getInstance().saveConfig();
            sender.sendMessage("§fCreeper Griefing turned §cOFF");
            sender.sendMessage("§estill kaboom but no blocks destroyed...");
            return true;
        }

        return true;
    }
}

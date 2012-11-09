package com.redcastlemedia.multitallented.spout.proxis.views.commands;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;

/**
 *
 * @author Multitallented
 */
public class ProxisCommands {
    private final Proxis proxis;
    public ProxisCommands(Proxis proxis) {
        this.proxis = proxis;
        
    }
    
    @Command(aliases = {"border"}, usage = "enable|disable|set [radius] [square|circle]", desc="controls the border")
    @CommandPermissions("dropletborder.control")
    public void commandRoot(CommandContext args, CommandSource source) {
        /*if (args.length() >= 1) {
            String mode = args.getString(0);
            if (mode.equals("enable")) {
                BorderConfiguration.ENABLED.setValue(true);
                source.sendMessage(ChatStyle.DARK_GREEN, "Border enabled.");
            } else if (mode.equals("disable")) {
                BorderConfiguration.ENABLED.setValue(false);
                source.sendMessage(ChatStyle.DARK_GREEN, "Border disabled.");
            } else if (mode.equals("set")) {
                if (args.length() >= 3) {
                    if (source instanceof Player) {
                        Player p = (Player) source;
                        double radius = args.getDouble(1);
                        BorderType type = BorderType.valueOf(args.getString(2).toUpperCase());
                        Point center = p.getTransform().getPosition();
                        BorderConfiguration.setCenter(center);
                        BorderConfiguration.setBorderType(type);
                        BorderConfiguration.RADIUS.setValue(radius);
                        source.sendMessage(ChatStyle.DARK_GREEN, "Border set successfully.");
                        sendinfo(source);
                    } else {
                        source.sendMessage(ChatStyle.DARK_RED, "This works ingame only.");
                    }
                } else {
                        source.sendMessage(ChatStyle.DARK_RED, "Not enough arguments for set (/border set <radius> <square|circle>).");
                }
            }
        } else {
                // Display information
                sendinfo(source);
        }*/
    }
}

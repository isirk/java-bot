package com.isirk.RoboSirk.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

@CommandInfo(
        name = "Ping",
        description = "Gets the bot's latency"
)
@Author("isirk")
public class PingCommand extends Command {

    public PingCommand()
    {
        this.name = "ping";
        this.help = "checks the bot's latency";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Pong! Average Latency is " + event.getJDA().getGatewayPing() + "ms");
    }

}
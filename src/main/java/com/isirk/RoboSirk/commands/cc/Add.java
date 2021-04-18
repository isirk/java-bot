package com.isirk.RoboSirk.commands.cc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Add extends Command {

    public Add()
    {
        this.name = "add";
        this.help = "Adds a custom command";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Add command works!");
    }
}


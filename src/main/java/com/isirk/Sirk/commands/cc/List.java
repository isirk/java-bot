package com.isirk.Sirk.commands.cc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class List extends Command {

    public List()
    {
        this.name = "list";
        this.help = "Lists the custom commands for this guild";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().addReaction("\uD83D\uDC4C").queue();
    }
}

package com.isirk.Sirk.commands.cc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class Add extends Command {

    public Add()
    {
        this.name = "add";
        this.help = "Adds a custom command";
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.MANAGE_SERVER};
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().addReaction("\uD83D\uDC4C").queue();
    }
}


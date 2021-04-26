package com.isirk.RoboSirk.commands.cc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class Edit extends Command {

    public Edit()
    {
        this.name = "edit";
        this.help = "Edits a custom command";
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.MANAGE_SERVER};
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Edit command works!");
    }
}


package com.isirk.RoboSirk.commands;

import com.isirk.RoboSirk.commands.cc.*;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class Custom extends Command {

    public Custom() {
        this.name = "cc";
        this.children = new Command[]{new Add(), new Edit(), new List(), new Remove()};
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Custom")
                .setDescription("Here are a list of sub-commands!\n\n`c!cc <sub-command>`")
                .setFooter("These commands require MANAGE SERVER permission")
                .setColor(0xB8E0FF);

        for(Command command : getChildren())
        {
            embed.addField(command.getName(), command.getHelp(), false);
        }

        event.reply(embed.build());
    }
}

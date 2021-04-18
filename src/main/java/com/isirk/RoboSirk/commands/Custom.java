package com.isirk.RoboSirk.commands;

import com.isirk.RoboSirk.commands.cc.Add;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class Custom extends Command {

    public Custom() {
        this.name = "cc";
        this.children = new Command[]{new Add()};
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Custom");
        embed.setDescription("Here are a list of sub-commands!\n\n`cc <sub-command>`");

        for(Command command : getChildren())
        {
            embed.addField(command.getName(), command.getHelp(), false);
        }

        event.reply(embed.build());
    }
}

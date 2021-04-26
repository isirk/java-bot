package com.isirk.RoboSirk.commands;

import com.isirk.RoboSirk.commands.dev.Guilds;
import com.isirk.RoboSirk.commands.dev.Shutdown;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;

public class Dev extends Command {

    EventWaiter waiter = new EventWaiter();

    public Dev() {
        this.name = "dev";
        this.ownerCommand = true;
        this.children = new Command[]{new Shutdown(), new Guilds(waiter)};
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Dev")
                .setDescription("Here are a list of sub-commands!\n\n`c!dev <sub-command>`")
                .setColor(0xB8E0FF);

        for(Command command : getChildren())
        {
            embed.addField(command.getName(), command.getHelp(), false);
        }

        event.reply(embed.build());
    }
}

package com.isirk.Sirk.commands;

import com.isirk.Sirk.commands.dev.Guilds;
import com.isirk.Sirk.commands.dev.Shutdown;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;

public class Dev extends Command {

    EventWaiter waiter = new EventWaiter();

    public Dev() {
        this.name = "dev";
        this.help = "Developer Commands";
        this.ownerCommand = true;
        this.children = new Command[]{new Shutdown(), new Guilds(waiter)};
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Dev")
                .setDescription(this.help + "\n\n```" + event.getClient().getPrefix() + "dev <sub-command>```")
                .setColor(0x7289DA);

        for(Command command : getChildren())
        {
            embed.addField(command.getName(), command.getHelp(), false);
        }

        event.reply(embed.build());
    }
}

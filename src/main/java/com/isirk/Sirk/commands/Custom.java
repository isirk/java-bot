package com.isirk.Sirk.commands;

import com.isirk.Sirk.commands.cc.*;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class Custom extends Command {

    public Custom() {
        this.name = "cc";
        this.help = "Custom Command Module";
        this.children = new Command[]{new Add(), new Edit(), new List(), new Remove()};
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Custom")
                .setDescription(this.help + "\n\n```" + event.getClient().getPrefix() + "cc <sub-command>```")
                .setFooter("These commands require MANAGE SERVER permission")
                .setColor(0x7289DA);

        for(Command command : getChildren())
        {
            embed.addField(command.getName(), command.getHelp(), false);
        }

        event.reply(embed.build());
    }
}

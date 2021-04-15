package com.isirk.RoboSirk.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ChooseCommand extends Command
{

    public ChooseCommand()
    {
        this.name = "choose";
        this.help = "make a decision";
        this.arguments = "<item> <item> ...";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        // check that the user provided choices
        if(event.getArgs().isEmpty())
        {
            event.replyWarning("You didn't give me any choices!");
        }
        else
        {
            // split the choices on all whitespace
            String[] items = event.getArgs().split("\\s+");

            // if there is only one option, have a special reply
            if(items.length==1)
                event.replyWarning("You only gave me one option, `"+items[0]+"`");

                // otherwise, pick a random response
            else
            {
                event.reply("I choose `"+items[(int)(Math.random()*items.length)]+"`");
            }
        }
    }

}

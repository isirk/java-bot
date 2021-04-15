package com.isirk.RoboSirk.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RoleCommand extends Command
{
    private final static String LINESTART = "\u25AB"; // ▫
    private final static String ROLE_EMOJI = "\uD83C\uDFAD"; // 🎭

    public RoleCommand()
    {
        this.name = "role";
        this.help = "shows info about a role";
        this.arguments = "<role>";
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        Role role;
        if(event.getArgs().isEmpty())
        {
            event.replyError("Please provide the name of a role!");
            return;
        }
        else
        {
            List<Role> found = FinderUtil.findRoles(event.getArgs(), event.getGuild());
            if(found.isEmpty())
            {
                event.replyError("I couldn't find the role you were looking for!");
                return;
            }
            else if(found.size()>1)
            {
                event.replyWarning(listOfRoles(found, event.getArgs()));
                return;
            }
            else
            {
                role = found.get(0);
            }
        }

        String title = (ROLE_EMOJI + " Information about **" + role.getName() + "**:")
                .replace("@everyone", "@\u0435veryone") // cyrillic e
                .replace("@here", "@h\u0435re") // cyrillic e
                .replace("discord.gg/", "dis\u0441ord.gg/"); // cyrillic c;;
        List<Member> list = role.isPublicRole() ? event.getGuild().getMembers() : event.getGuild().getMembersWithRoles(role);
        Color color = role.getColor();
        StringBuilder desr = new StringBuilder(LINESTART + "ID: **" + role.getId() + "**\n"
                + LINESTART + "Creation: **" + role.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME)+"**\n"
                + LINESTART + "Position: **" + role.getPosition()+"**\n"
                + LINESTART + "Color: **#" + (color==null ? "000000" : Integer.toHexString(color.getRGB()).toUpperCase().substring(2)) + "**\n"
                + LINESTART + "Mentionable: **" + role.isMentionable() + "**\n"
                + LINESTART + "Hoisted: **" + role.isHoisted() + "**\n"
                + LINESTART + "Managed: **" + role.isManaged() + "**\n"
                + LINESTART + "Permissions: ");
        if(role.getPermissions().isEmpty())
            desr.append("None");
        else
            desr.append(role.getPermissions().stream().map(p -> "`, `"+p.getName()).reduce("", String::concat).substring(3)).append("`");
        desr.append("\n").append(LINESTART).append("Members: **").append(list.size()).append("**\n");
        if(list.size() * 24 <= 2048-desr.length())
            list.forEach(m -> desr.append("<@").append(m.getUser().getId()).append("> "));

        event.reply(new MessageBuilder()
                .append(title)
                .setEmbed(new EmbedBuilder()
                        .setDescription(desr.toString().trim())
                        .setColor(role.getColor()).build())
                .build());
    }

    private static String listOfRoles(List<Role> list, String query)
    {
        String out = String.format("**Multiple roles found matching \"%s\":**", query);
        for(int i = 0; i < 6 && i < list.size(); i++)
            out += "\n - " + list.get(i).getName() + " (ID:" + list.get(i).getId() + ")";
        if(list.size() > 6)
            out += "\n**And " + (list.size() - 6) + " more...**";
        return out;
    }
}

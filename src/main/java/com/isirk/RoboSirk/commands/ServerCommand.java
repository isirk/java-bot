package com.isirk.RoboSirk.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import java.time.format.DateTimeFormatter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class ServerCommand extends Command
{
    private final static String LINESTART = "\u25AB"; // ▫
    private final static String GUILD_EMOJI = "\uD83D\uDDA5"; // 🖥
    private final static String NO_REGION = "\u2754";

    public ServerCommand()
    {
        this.name = "server";
        this.aliases = new String[]{"guildinfo"};
        this.help = "shows server info";
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        Guild guild = event.getGuild();
        Member owner = guild.getOwner();
        long onlineCount = guild.getMembers().stream().filter(u -> u.getOnlineStatus() != OnlineStatus.OFFLINE).count();
        long botCount = guild.getMembers().stream().filter(m -> m.getUser().isBot()).count();
        EmbedBuilder builder = new EmbedBuilder();
        String title = (GUILD_EMOJI + " Information about **" + guild.getName() + "**:")
                .replace("@everyone", "@\u0435veryone") // cyrillic e
                .replace("@here", "@h\u0435re") // cyrillic e
                .replace("discord.gg/", "dis\u0441ord.gg/"); // cyrillic c;
        String verif;
        switch(guild.getVerificationLevel())
        {
            case VERY_HIGH:
                verif = "VERY HIGH";
                break;
            case HIGH:
                verif = "HIGH";
                break;
            default:
                verif = guild.getVerificationLevel().name();
                break;
        }
        String str = LINESTART + "ID: **" + guild.getId() + "**\n"
                + LINESTART + "Owner: " + (owner == null ? "Unknown" : "**" + owner.getUser().getName() + "**#" + owner.getUser().getDiscriminator()) + "\n"
                + LINESTART + "Location: " + (guild.getRegion().getEmoji().isEmpty() ? NO_REGION : guild.getRegion().getEmoji()) + " **" + guild.getRegion().getName() + "**\n"
                + LINESTART + "Creation: **" + guild.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "**\n"
                + LINESTART + "Users: **" + guild.getMemberCache().size() + "** (" + onlineCount + " online, " + botCount + " bots)\n"
                + LINESTART + "Channels: **" + guild.getTextChannelCache().size() + "** Text, **" + guild.getVoiceChannelCache().size() + "** Voice, **" + guild.getCategoryCache().size() + "** Categories\n"
                + LINESTART + "Verification: **" + verif + "**";
        if(!guild.getFeatures().isEmpty())
            str += "\n" + LINESTART + "Features: **" + String.join("**, **", guild.getFeatures()) + "**";
        if(guild.getSplashId() != null)
        {
            builder.setImage(guild.getSplashUrl() + "?size=1024");
            str += "\n" + LINESTART + "Splash: ";
        }
        if(guild.getIconUrl()!=null)
            builder.setThumbnail(guild.getIconUrl());
        builder.setColor(owner == null ? null : owner.getColor());
        builder.setDescription(str);
        event.reply(new MessageBuilder().append(title).setEmbed(builder.build()).build());
    }
}
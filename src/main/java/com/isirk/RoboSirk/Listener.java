package com.isirk.RoboSirk;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String msg = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        String id = event.getJDA().getSelfUser().getId();
        if (msg.startsWith("<@%s>".formatted(id)) || msg.startsWith("<@!%s>".formatted(id)))
        {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Help")
                    .setDescription("A simple custom commands bot")
                    .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                    .setFooter("Made by isirk#0001", "https://asksirk.com/img/isirk.gif")
                    .addField("Commands", "`cc` - Custom Commands", false)
                    .addField("Stats", "Guilds: `%d`\nUsers: `%s`\nJDA: [`%s`](https://github.com/DV8FromTheWorld/JDA)".formatted(event.getJDA().getGuilds().size(), event.getJDA().getUsers().size(), JDAInfo.VERSION), false);

            channel.sendMessage(embed.build()).queue();
        }
    }
}

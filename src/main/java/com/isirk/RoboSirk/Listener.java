package com.isirk.RoboSirk;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

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
                    .setColor(0x7289DA)
                    .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                    .setFooter("Made by isirk#0001", "https://asksirk.com/img/isirk.gif")
                    .addField("Commands", "`cc` - Custom Commands", false)
                    .addField("Stats", "Guilds: `%d`\nUsers: `%s`\nJDA: [`%s`](https://github.com/DV8FromTheWorld/JDA)".formatted(event.getJDA().getGuilds().size(), event.getJDA().getUsers().size(), JDAInfo.VERSION), false);

            channel.sendMessage(embed.build()).queue();
        }
        else if (msg.startsWith("!")) {
            String[] message = event.getMessage().getContentRaw().replace("!", "").split(" ");
            String cmd = message[0];
            Map<String, String> cc = new HashMap<>();
            cc.put("test", "test");
            if (cc.containsKey(cmd)) {
                event.getChannel().sendMessage(cc.get(cmd)).queue();
            }
        }
    }
}

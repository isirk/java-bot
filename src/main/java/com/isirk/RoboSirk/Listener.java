package com.isirk.RoboSirk;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

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
                    .setColor(0xB8E0FF)
                    .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                    .setFooter("Made by isirk#0001", "https://asksirk.com/img/isirk.gif")
                    .addField("Commands", "`c!cc` - Custom Commands", false)
                    .addField("Stats", "Guilds: `%d`\nPing: `%d`\nJDA: [`%s`](https://github.com/DV8FromTheWorld/JDA)".formatted(event.getJDA().getGuilds().size(), event.getJDA().getGatewayPing(), JDAInfo.VERSION), false)
                    .addField("Links", "[Invite!](https://discord.com/oauth2/authorize?client_id=819627068949135400&permissions=2147846208&scope=bot)\n[Website](https://asksirk.com/robosirk)", false);

            channel.sendMessage(embed.build()).queue();
        }
        else if (msg.startsWith("c!")) {
            String[] message = event.getMessage().getContentRaw().replace("c!", "").split(" ");
            String cmd = message[0];
            HashMap<String, String> cc = new HashMap<>();
            cc.put("custom", "-e Custom Commands are cool!");
            if (cc.containsKey(cmd)) {
                if (cc.get(cmd).contains("-e")) {
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription(cc.get(cmd).replace("-e", "")).setColor(0xB8E0FF).build()).queue();
                } else {
                    event.getChannel().sendMessage(cc.get(cmd)).queue();
                }
            }
        }
    }
}

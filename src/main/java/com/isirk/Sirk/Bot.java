package com.isirk.Sirk;

import com.isirk.Sirk.commands.Custom;
import com.isirk.Sirk.commands.Dev;
import com.isirk.Sirk.listeners.Listener;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException {
        String token = new String(Files.readAllBytes(Path.of("src/main/resources/token.txt")));
        CommandClientBuilder builder = new CommandClientBuilder()
                .setPrefix("^")
                .setOwnerId("542405601255489537")
                .setServerInvite("https://discord.gg/7yZqHfG")
                .setEmojis("\u2705", "\u26a0", "\u203c")
                .useHelpBuilder(false)
                .setActivity(Activity.playing("Custom Commands"))
                .addCommands(
                        new Dev(),
                        new Custom()
                );

        JDABuilder.createDefault(token)
                //.enableIntents(GatewayIntent.GUILD_MEMBERS)
                //.setMemberCachePolicy(MemberCachePolicy.ALL)
                .setStatus(OnlineStatus.IDLE)
                .setActivity(Activity.playing("Loading..."))
                .addEventListeners(new Listener(), builder.build())
                .build();
    }
}

package com.isirk.RoboSirk;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import com.isirk.kotlinbot.modules.InfoModule;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException {
        String token = new String(Files.readAllBytes(Path.of("token.txt")));
       CommandClientBuilder builder = new CommandClientBuilder()
                .setPrefix("s^")
                .setOwnerId("542405601255489537")
                .useDefaultGame()
                .useHelpBuilder(true)
                .setServerInvite("https://discord.gg/7yZqHfG")
                .setEmojis("✅","⚠", "‼");

        JDABuilder.createDefault("")
                .addEventListeners(builder.build())
                .setEnabledIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
    }
}

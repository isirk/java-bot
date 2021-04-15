package com.isirk.RoboSirk;

import com.isirk.RoboSirk.commands.*;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException {
        String token = new String(Files.readAllBytes(Path.of("src/main/resources/token.txt")));
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder builder = new CommandClientBuilder()
                .setPrefix("s^")
                .setOwnerId("542405601255489537")
                .useHelpBuilder(true)
                .setServerInvite("https://discord.gg/7yZqHfG")
                .setEmojis("\u2705", "\u26a0", "\u203c")
                .addCommands(
                        new AboutCommand(),
                        new GuildsCommand(waiter),
                        new PingCommand(),
                        new RoleCommand(),
                        new ServerCommand(),
                        new ShutdownCommand(),
                        new ChooseCommand()
                );

        JDABuilder.createDefault(token)
                .addEventListeners(waiter, builder.build())
                .build();
    }
}

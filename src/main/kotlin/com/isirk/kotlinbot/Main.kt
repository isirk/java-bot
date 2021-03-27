package com.isirk.kotlinbot

import com.isirk.kotlinbot.modules.InfoModule
import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import java.util.*

fun main(){

    val builder: CommandClientBuilder = CommandClientBuilder()
        .setPrefix("k!")
        .setOwnerId("542405601255489537")
        .addAnnotatedModules(InfoModule())
        .useDefaultGame()
        .useHelpBuilder(true)
        .setServerInvite("https://discord.gg/7yZqHfG")
        .setEmojis("✅","⚠", "‼")

    JDABuilder.createDefault("ODE5NjI3MDY4OTQ5MTM1NDAw.YEpXMQ.G_B162jtVUxcPYvC5_nkecVgYR8")
        .addEventListeners(builder.build())
        .setEnabledIntents(EnumSet.allOf(GatewayIntent::class.java))
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .build()
}
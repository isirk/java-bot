package com.isirk.kotlinbot

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import com.isirk.kotlinbot.modules.InfoModule
import java.io.File
import java.util.*

fun main(){

    val config: ConfigDataClass = jacksonObjectMapper().readValue<ConfigDataClass>(File("./config.json").readText())

    val builder: CommandClientBuilder = CommandClientBuilder()
        .setPrefix(config.prefix)
        .setOwnerId(config.ownerId)
        .addAnnotatedModules(InfoModule())
        .useDefaultGame()
        .useHelpBuilder(true)
        .setServerInvite(config.serverInvite)
        .setEmojis("✅","⚠", "‼")

    JDABuilder.createDefault(config.token)
        .addEventListeners(builder.build())
        .setEnabledIntents(EnumSet.allOf(GatewayIntent::class.java))
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .build()
}
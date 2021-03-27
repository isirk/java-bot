package com.isirk.kotlinbot.extensions

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.MessageBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import java.awt.Color

/**
 * Builds an EmbedBuilder into a message object
 *
 * @return Message for replying.
 */
fun EmbedBuilder.toMessage(): Message{
    return MessageBuilder().setEmbed(this.build()).build()
}

/**
 * Turns a user into name#discriminator, will return "" if the member is null.
 *
 * @return The member in a name#discriminator format or whitespace if the member is null.
 */
fun Member?.toNameDiscrim(): String {
    if (this == null) return ""
    val user = this.user
    return "${user.name}#${user.discriminator}"
}

/**
 * Makes an EmbedBuilder with the hex 0x2F3136, which makes it "Invisible" on dark-mode clients.
 */
fun EmbedBuilder.withInvisible(): EmbedBuilder{
    return this.setColor(0x2F3136)
}
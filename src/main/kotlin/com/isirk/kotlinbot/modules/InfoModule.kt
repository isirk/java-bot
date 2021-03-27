package com.isirk.kotlinbot.modules

import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.command.annotation.JDACommand
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import com.isirk.kotlinbot.extensions.toMessage
import com.isirk.kotlinbot.extensions.toNameDiscrim
import com.isirk.kotlinbot.extensions.withInvisible
import java.lang.Exception
import java.time.format.DateTimeFormatter

@JDACommand.Module(value = [ "ServerInfoCommand", "MemberInfoCommand" ])

class InfoModule {

    val bytesRatio: Int = 1000000

    /**
     * Tries to get the member via 2 ways.
     * From `name#discrim`
     * From `ID`
     *
     * @param arg: the arg to parse
     * @param guild: the guild that the member is parsed from
     * @returns the parsed member or null
     */
    private fun resolveMember(arg: String, guild: Guild): Member?{
        return try {
            val member: Member? = guild.getMemberById(arg)
            member
        } catch (e: Exception) {

            guild.getMembersByEffectiveName(arg, true).firstOrNull()
        }
    }

    @JDACommand(
        name = ["serverinfo", "guildinfo"],
        help = "Sends info about the server"
    )
    fun ServerinfoCommand(event: CommandEvent){
        val guild: Guild = event.guild

        val builder = EmbedBuilder()
            .setTitle("Info about `${guild.name}`")
            .setDescription(StringBuilder()
                .append("Owner: ${guild.owner.toNameDiscrim()}").appendLine()
                .append("ID: ${guild.id}").appendLine()
                .append("Created At: ${guild.timeCreated.format(DateTimeFormatter.RFC_1123_DATE_TIME)}").appendLine()
                .append("Verification Level: ${guild.verificationLevel.name.capitalize()}").appendLine()
                .append("Region: ${guild.regionRaw.capitalize()}")
                .toString())
            .setThumbnail(guild.iconUrl)
            .addField("Stats", StringBuilder()
                .append("Member Count: ${guild.memberCount}").appendLine()
                .append("Max Member Count: ${guild.maxMembers}").appendLine()
                .append("Max Emotes: ${guild.maxEmotes}").appendLine()
                .append("Max Filesize: ${guild.maxFileSize/bytesRatio} MB").appendLine()
                .append("Emojis: ${guild.emotes.count()}")
                .toString(),
                false
            )

        event.reply(builder.toMessage())
    }

    @JDACommand(
        name = ["userinfo", "whois", "memberinfo"],
        arguments = "member"
    )
    fun MemberInfoCommand(event: CommandEvent){
        val member: Member = resolveMember(event.args, event.guild)
            ?: return event.replyWarning("Member not found.")

    }
}

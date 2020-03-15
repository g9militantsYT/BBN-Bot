package com.bbn.bot;

import com.bbn.bot.core.Config;
import com.bbn.bot.core.Sender;
import com.bbn.bot.listeners.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
* @author GregTCLTK / Skidder
 */

public class BBNBot {

    public static Config config = new Config("./BBN_config.json");

    public static void main(String[] args) {

        Sender sender = new Sender();
        config.load();

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setActivity(Activity.streaming("on the BBN", "https://twitch.tv/bigbotnetwork"))
                .setToken(config.getToken())
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListeners(new MemberJoinListener(),
                        new MessageReceiveListener(),
                        new ReactionAddListener(),
                        new MemberLeaveListener(),
                        new OnlineStatusListener(sender));

        try {
            builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
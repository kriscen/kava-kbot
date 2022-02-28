package com.kbot.bot.entity;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.*;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 假机器人
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
public class KrisBot implements Bot {
    @NotNull
    @Override
    public Friend getAsFriend() {
        return null;
    }

    @NotNull
    @Override
    public Stranger getAsStranger() {
        return null;
    }

    @NotNull
    @Override
    public BotConfiguration getConfiguration() {
        return null;
    }

    @NotNull
    @Override
    public EventChannel<BotEvent> getEventChannel() {
        return null;
    }

    @NotNull
    @Override
    public ContactList<Friend> getFriends() {
        return null;
    }

    @NotNull
    @Override
    public ContactList<Group> getGroups() {
        return null;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @NotNull
    @Override
    public MiraiLogger getLogger() {
        return null;
    }

    @NotNull
    @Override
    public String getNick() {
        return null;
    }

    @NotNull
    @Override
    public ContactList<OtherClient> getOtherClients() {
        return null;
    }

    @NotNull
    @Override
    public ContactList<Stranger> getStrangers() {
        return null;
    }

    @Override
    public void close(@Nullable Throwable throwable) {

    }

    @Nullable
    @Override
    public Object login(@NotNull Continuation<? super Unit> continuation) {
        return null;
    }

    @NotNull
    @Override
    public CoroutineContext getCoroutineContext() {
        return null;
    }
}

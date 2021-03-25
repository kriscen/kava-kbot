package com.kbot.event;


import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Program Name: kava-kbo
 * <p>
 * Description: 
 * <p>
 * Created by kris on 2021/3/16
 *
 * @author kris
 */
@Slf4j
public class GroupEvents extends SimpleListenerHost {


    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("RecallEvent Error:{}", exception.getMessage());
    }


    /**
     * 群成员主动加群事件
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMemberJoinGroupBySelf(@NotNull MemberJoinEvent.Active event) {
        log.info("onMemberJoinGroupBySelf....");
        // 表示继续监听事件
        return ListeningStatus.LISTENING;
    }

    /**
     * 群成员被邀请加群事件
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMemberJoinGroupByOther(@NotNull MemberJoinEvent.Invite event) {
        log.info("onMemberJoinGroupByOther....");
        // 表示继续监听事件
        return ListeningStatus.LISTENING;
    }


    /**
     * 群成员主动离群事件
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMemberLeaveGroupBySelf(@NotNull MemberLeaveEvent.Quit event) {
        log.info("onMemberLeaveGroupBySelf....");
        return ListeningStatus.LISTENING;
    }

    /**
     * 群成员被踢出群事件
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMemberLeaveGroupByOther(@NotNull MemberLeaveEvent.Kick event) {
        log.info("onMemberLeaveGroupByOther....");
        return ListeningStatus.LISTENING;
    }

}

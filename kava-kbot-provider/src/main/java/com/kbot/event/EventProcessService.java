package com.kbot.event;

import net.mamoe.mirai.event.events.*;
import org.springframework.stereotype.Service;

/**
 * Program Name: kava-kbot
 * <p>
 * Description:
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Service
public class EventProcessService {
    /**
     * 所有消息处理
     * @param event 消息事件
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    public void onMessage(MessageEvent event) throws Exception {

    }

    /**
     * 好友私聊消息事件处理
     * @param event 消息事件
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    public void onFriendMessage(FriendMessageEvent event) throws Exception {
        event.getSubject().sendMessage("hello word");
    }

    /**
     * 群聊消息事件处理
     * @param event 消息事件
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    public void onGroupMessage(GroupMessageEvent event) throws Exception {

    }

    /**
     * 群成员主动加群事件
     * @param event 消息事件
     */
    public void onMemberJoinGroupBySelf(MemberJoinEvent.Active event) {

    }

    /**
     * 群成员被邀请加群事件
     * @param event 消息事件
     */
    public void onMemberJoinGroupByOther(MemberJoinEvent.Invite event) {

    }

    /**
     * 群成员主动离群事件
     * @param event 消息事件
     */
    public void onMemberLeaveGroupBySelf(MemberLeaveEvent.Quit event) {

    }

    /**
     * 群成员被踢出群事件
     * @param event 消息事件
     */
    public void onMemberLeaveGroupByOther(MemberLeaveEvent.Kick event) {

    }
}

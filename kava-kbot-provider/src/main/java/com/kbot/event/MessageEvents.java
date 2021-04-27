package com.kbot.event;

import com.kbot.command.BaseCommand;
import com.kbot.command.friend.FriendCommand;
import com.kbot.command.group.GroupCommand;
import com.kbot.command.grouptempmessage.GroupTempMessageCommand;
import com.kbot.config.BotContainer;
import com.kbot.service.CommandHandleService;
import com.kbot.service.GlobalEventHandleService;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: message event
 * <p>
 * Created by kris on 2021/3/22
 *
 * @author kris
 */
@Slf4j
public class MessageEvents extends SimpleListenerHost {

    @Autowired
    private CommandHandleService commandHandleService;

    @Autowired
    private BotContainer botContainer;

    @Autowired
    private GlobalEventHandleService globalEventHandleService;

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        log.error("RecallEvent Error:{}", exception.getMessage());
    }


    /**
     * 所有消息处理
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MessageEvent event) throws Exception {
        log.info("onMessage....");
        User sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();

        //是否指令模式
        if (!commandHandleService.isCommand(oriMsg)) {
            //触发全局动作
            globalEventHandleService.execute(sender, oriMsg, event.getMessage(), event.getSubject());
            return ListeningStatus.LISTENING;
        }
        BaseCommand command = commandHandleService.getCommand(oriMsg, botContainer.getEverywhereCommands());
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, oriMsg, event.getMessage(), event.getSubject());
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        // 表示继续监听事件
        return ListeningStatus.LISTENING;
    }


    /**
     * 好友私聊消息事件处理
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onFriendMessage(@NotNull FriendMessageEvent event) throws Exception {
        log.info("onFriendMessage....");

        Friend sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();


        //是否指令模式
        if (!commandHandleService.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }
        FriendCommand command = (FriendCommand) commandHandleService.getCommand(oriMsg, botContainer.getFriendCommands());
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender,oriMsg, event.getMessage(), event.getSubject());
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        //事件拦截 防止everywhere消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }


    /**
     * 群聊消息事件处理
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onGroupMessage(@NotNull GroupMessageEvent event) throws Exception {
        log.info("onGroupMessage....");
        Member sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (botContainer.getBlackList().contains(sender.getId())) {
            return ListeningStatus.LISTENING;
        }

        //是否指令模式
        if (!commandHandleService.isCommand(oriMsg)) {
            // 非指令处理其他业务
            return ListeningStatus.LISTENING;
        }
        GroupCommand command = (GroupCommand) commandHandleService.getCommand(oriMsg, botContainer.getGroupCommands());
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, oriMsg, event.getMessage(), event.getSubject());
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        //事件拦截 防止公共消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }

    @NotNull
    @EventHandler
    public ListeningStatus onTempMessage(@NotNull GroupTempMessageEvent event) throws Exception {
        Member sender = event.getSender();

        String oriMsg = event.getMessage().contentToString();

        //是否指令模式
        if (!commandHandleService.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }

        GroupTempMessageCommand command = (GroupTempMessageCommand) commandHandleService.getCommand(oriMsg, botContainer.getGroupTempMessageCommands());
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, oriMsg, event.getMessage(), sender);
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        //事件拦截 防止公共消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }
}

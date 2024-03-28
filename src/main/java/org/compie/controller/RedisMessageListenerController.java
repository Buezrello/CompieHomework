package org.compie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RedisMessageListenerController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    public RedisMessageListenerController(RedisMessageListenerContainer redisMessageListenerContainer) {
        this.redisMessageListenerContainer = redisMessageListenerContainer;
        this.redisMessageListenerContainer.addMessageListener((message, pattern) -> {
            String body = new String(message.getBody());
            messagingTemplate.convertAndSend("/topic/items", body);
        }, new ChannelTopic("databaseChanges"));
    }
}

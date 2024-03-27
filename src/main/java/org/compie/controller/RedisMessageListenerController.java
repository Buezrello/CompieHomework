//package org.compie.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class RedisMessageListenerController {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//    @Autowired
//    private RedisMessageListenerContainer redisMessageListenerContainer;
//
//    @Autowired
//    public RedisMessageListenerController(RedisMessageListenerContainer redisMessageListenerContainer) {
//        this.redisMessageListenerContainer = redisMessageListenerContainer;
//        this.redisMessageListenerContainer.addMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message, byte[] pattern) {
//                String channel = new String(message.getChannel());
//                String body = new String(message.getBody());
//                messagingTemplate.convertAndSend("/topic/items", body);
//            }
//        }, new ChannelTopic("databaseChanges"));
//    }
//}

//package org.compie.listener;
//
//import org.compie.model.balldontlie.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class DatabaseEventListener {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @EventListener
//    @Transactional(readOnly = true)
//    public void handleDatabaseChange(Object event) {
//        if (event instanceof Data) {
//            Data data = (Data) event;
//            redisTemplate.convertAndSend("databaseChanges",
//                    "Database change detected: " + data.getId());
//        }
//    }
//}

package org.musinsa.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {
    @Autowired CategorySingleton categorySingleton;

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        categorySingleton.refreshCategoryList();
    }
}
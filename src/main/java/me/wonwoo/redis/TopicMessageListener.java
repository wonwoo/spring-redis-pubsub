package me.wonwoo.redis;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by wonwoo on 2016. 4. 22..
 */
@Slf4j
public class TopicMessageListener implements MessageListener {

  private RedisTemplate redisTemplate;

  @Autowired
  public void setRedisTemplate(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void onMessage(Message message, byte[] bytes) {
    byte[] body = message.getBody();
    String str = (String) redisTemplate.getStringSerializer().deserialize(body);
    log.info("key : {}, message : {}", new String(bytes), str);
  }
}

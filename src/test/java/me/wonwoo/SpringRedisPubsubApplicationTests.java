package me.wonwoo;

import me.wonwoo.redis.JsonRedisTemplate;
import me.wonwoo.redis.SendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRedisPubsubApplicationTests {

  @Autowired
  private JsonRedisTemplate jsonRedisTemplate;

  @Test
  public void pubsub() {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setName("wonwoo");
    sendMessage.setEmail("wonwoo@test.com");
    jsonRedisTemplate.convertAndSend("sendMessage", sendMessage);
  }

}

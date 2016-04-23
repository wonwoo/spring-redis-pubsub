package me.wonwoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.redis.JsonRedisTemplate;
import me.wonwoo.redis.MethodMessageListener;
import me.wonwoo.redis.SendMessage;
import me.wonwoo.redis.TopicMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class SpringRedisPubsubApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRedisPubsubApplication.class, args);
  }

  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                 MessageListenerAdapter listenerAdapter) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new PatternTopic("sendMessage"));
    container.addMessageListener(topicMessageListener(), new PatternTopic("sendMessage*"));
    return container;
  }

  @Bean
  public TopicMessageListener topicMessageListener() {
    return new TopicMessageListener();
  }

  @Bean
  public MessageListenerAdapter listenerAdapter(MethodMessageListener methodMessageListener) {
    return new MessageListenerAdapter(methodMessageListener, "sendMessage");
  }

  @Bean
  public MethodMessageListener methodMessageListener() {
    return new MethodMessageListener();
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public JsonRedisTemplate jsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
    return new JsonRedisTemplate<>(connectionFactory, objectMapper, Object.class);
  }
}

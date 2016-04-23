package me.wonwoo.redis;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by wonwoo on 2016. 4. 22..
 */
@Slf4j
public class MethodMessageListener {

  public void sendMessage(String message) {
    log.info(message);
  }
}

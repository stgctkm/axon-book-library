package book.repository;

import book.events.BookCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBookProjection {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @EventHandler
  public void book(BookCreatedEvent event) {
    logger.info("👺👺👺 EventHandler Projecting Added Book By BookCreatedEvent. book = {}", event);

    // ここで投影する
  }

}

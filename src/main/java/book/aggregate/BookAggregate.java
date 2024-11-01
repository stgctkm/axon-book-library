package book.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import book.commands.RegisterBookCommand;
import book.events.BookCreatedEvent;
import java.util.List;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@Aggregate
public class BookAggregate {

  private Integer libraryId;

  private String title;

  Logger logger = LoggerFactory.getLogger(this.getClass());
//  private List<String> isbnBooks;
  @AggregateIdentifier
  private String isbn;

  @CommandHandler
  public BookAggregate(RegisterBookCommand command) {
    logger.info("** CommandHandler RegisterBookCommand command = {}", command);
    Assert.notNull(command.libraryId(), "ID should not be null");
    Assert.notNull(command.isbn(), "Book ISBN should not be null");

    apply(new BookCreatedEvent(command.libraryId(), command.isbn(), command.title()));

  }

  @EventSourcingHandler
  private void addBook(BookCreatedEvent event) {
    logger.info("** EventSourcingHandler BookCreatedEvent Adding book = {}", event);
    libraryId = event.libraryId();
    title = event.title();
    isbn = event.isbn();
  }



}

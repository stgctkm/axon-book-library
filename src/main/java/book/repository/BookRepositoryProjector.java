package book.repository;

import book.events.BookCreatedEvent;
import book.models.BookBean;
import book.queries.GetBooksQuery;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookRepositoryProjector {

	private final BookRepository bookRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public BookRepositoryProjector(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@EventHandler
	public void addBook(BookCreatedEvent event) throws Exception {
		logger.info("** Event handler bookCreatedEvent library = {}, isbn = {}, title = {}", event.libraryId(), event.isbn(), event.title());

		BookEntity book = new BookEntity(event.isbn(), event.libraryId(), event.title());
		bookRepository.save(book);
	}

	@QueryHandler
	public List<BookBean> getBooks(GetBooksQuery query) {
		logger.info("** Query Handler. library = {}", query.libraryId());
		List<BookBean> books =
				bookRepository.findByLibraryId(query.libraryId())
						.stream()
						.map(toBook())
						.toList();
		return books;
	}

	private Function<BookEntity, BookBean> toBook() {
		return e -> {
			BookBean book = new BookBean(e.isbn(), e.title());
			return book;
		};
	}
}

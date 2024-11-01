package book;

import book.aggregate.Library;
import book.commands.RegisterBookCommand;
import book.commands.RegisterLibraryCommand;
import book.models.BookBean;
import book.models.LibraryBean;
import book.queries.GetBooksQuery;
import book.queries.GetLibraryQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryRestController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public LibraryRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping("/api/library")
	public String addLibrary(@RequestBody LibraryBean library) {
		logger.info("** libraryId = {}", library.libraryId());
		logger.info("** libraryName = {}", library.name());
		commandGateway.send(new RegisterLibraryCommand(library.libraryId(), library.name()));
		return "Saved";
	}

	@GetMapping("/api/library/{library}")
	public Library library(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		CompletableFuture<Library> future = queryGateway.query(new GetLibraryQuery(library), Library.class);
		return future.get();
	}

	@PostMapping("/api/library/{library}/book")
	public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
		logger.info("** libraryId = {}", library);
		logger.info("** book.isbn= {}", book.isbn());
		logger.info("** book.title= {}", book.title());
		commandGateway.send(new RegisterBookCommand(library, book.isbn(), book.title()));
		return "Saved";
	}

//	@GetMapping("/api/library/{library}/book")
//	public CompletableFuture<List<BookBean>> completableFutureBooks(@PathVariable Integer library) throws InterruptedException, ExecutionException {
//		return queryGateway.query(
//						new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)
//				);
//	}

	@GetMapping("/api/library/{library}/book")
	public List<BookBean> books(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		return queryGateway.query(
				new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)
		).get();
	}

}

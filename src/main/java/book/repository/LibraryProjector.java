package book.repository;

import book.queries.GetLibraryQuery;
import book.aggregate.Library;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LibraryProjector {
	private final Repository<Library> libraryRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public LibraryProjector(Repository<Library> libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@QueryHandler
	public Library getLibrary(GetLibraryQuery query) throws InterruptedException, ExecutionException {
		logger.info("** QueryHandler handling GetLibraryQuery library = {}", query.libraryId());
		CompletableFuture<Library> future = new CompletableFuture<>();
		libraryRepository.load(String.valueOf(query.libraryId())).execute(future::complete);
		return future.get();
	}

}

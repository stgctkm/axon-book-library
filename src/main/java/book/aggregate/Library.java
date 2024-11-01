package book.aggregate;

import book.commands.RegisterLibraryCommand;
import book.events.LibraryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

@Aggregate
public class Library {

	@AggregateIdentifier
	private Integer libraryId;

	private String name;


	protected Library() {
		// For Axon instantiation
	}

	@CommandHandler
	public Library(RegisterLibraryCommand cmd) {
		Assert.notNull(cmd.libraryId(), "ID should not be null");
		Assert.notNull(cmd.name(), "Name should not be null");

		AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.libraryId(), cmd.name()));
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}


	@EventSourcingHandler
	private void handleCreatedEvent(LibraryCreatedEvent event) {
		libraryId = event.libraryId();
		name = event.name();
	}


}

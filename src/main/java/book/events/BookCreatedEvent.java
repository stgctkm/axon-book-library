package book.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record BookCreatedEvent(
		@TargetAggregateIdentifier
		Integer libraryId,
				String isbn,
				String title

) {
}

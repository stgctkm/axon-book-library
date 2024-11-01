package book.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record RegisterBookCommand(

	@TargetAggregateIdentifier
	Integer libraryId,
	String isbn,
	String title
) {
}

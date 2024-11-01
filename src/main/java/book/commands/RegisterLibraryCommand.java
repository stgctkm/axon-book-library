package book.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record RegisterLibraryCommand(

	@TargetAggregateIdentifier
	Integer libraryId,
	String name
) {
}

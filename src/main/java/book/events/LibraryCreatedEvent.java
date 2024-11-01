package book.events;


public record LibraryCreatedEvent(
		Integer libraryId,
		String name
) {
}

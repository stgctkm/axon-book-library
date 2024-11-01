package book.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity
//public record BookEntity(
//    @Id
//    String bookIsbn,
//    @Column
//    int libraryId,
//    @Column
//    String title
//) {
//
//}

@Entity
public class BookEntity {

  @Id
  String bookIsbn;
  @Column
  int libraryId;
  @Column
  String title;

  public BookEntity() {
  }

  public BookEntity(String bookIsbn, int libraryId, String title) {
    this.bookIsbn = bookIsbn;
    this.libraryId = libraryId;
    this.title = title;
  }

  public String isbn() {
    return bookIsbn;
  }

  public int libraryId() {
    return libraryId;
  }

  public String title() {
    return title;
  }


  public String getBookIsbn() {
    return bookIsbn;
  }

  public int getLibraryId() {
    return libraryId;
  }

  public String getTitle() {
    return title;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }

  public void setLibraryId(int libraryId) {
    this.libraryId = libraryId;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
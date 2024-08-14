
import config.SpringConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class Main {

    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = context.getBean(BookRepository.class);

    public static void main(String[] args) {
        updateAuthorById(2, "Hello");
    }

    private static void deleteAllBooks() {
        bookRepository.deleteAll();
        System.out.println("All books have been deleted.");
    }

    private static void createNewBook() {
        // Prepare data
        entity.BookEntity bookEntity = new BookEntity();
        bookEntity.setName("B1");
        bookEntity.setAuthor("Alex");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        // Save the book entity
        entity.BookEntity result = bookRepository.save(bookEntity);

        entity.BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setName("B2");
        bookEntity2.setAuthor("Robert");
        bookEntity2.setCategory("English books");
        bookEntity2.setIsbn("ISIBF1219323");
        bookEntity2.setNumberOfPage(234);
        bookEntity2.setPrice(20.5);
        bookEntity2.setPublishDate(LocalDate.parse("2016-08-25"));

        // Save the book entity
        entity.BookEntity result2 = bookRepository.save(bookEntity2);

        entity.BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setName("A1");
        bookEntity3.setAuthor("Roger");
        bookEntity3.setCategory("License books");
        bookEntity3.setIsbn("ISIBF1219323");
        bookEntity3.setNumberOfPage(234);
        bookEntity3.setPrice(20.5);
        bookEntity3.setPublishDate(LocalDate.parse("2016-08-25"));

        // Save the book entity
        entity.BookEntity result3 = bookRepository.save(bookEntity3);
        if (result != null) {
            System.out.println("A new book saved successfully, book ID = " + result.getId());
        }
    }
    private static void findBookById(int id) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if (bookEntityOptional.isPresent()) {
            BookEntity bookEntity = bookEntityOptional.get();
            System.out.println("Found book with ID " + id + ": " + bookEntity);
        } else {
            System.out.println("No book found with ID " + id);
        }
    }

    private static void updateAuthorById(int id, String newAuthor) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if (bookEntityOptional.isPresent()) {
            BookEntity bookEntity = bookEntityOptional.get();
            bookEntity.setAuthor(newAuthor);
            bookRepository.save(bookEntity); // Lưu lại thay đổi
            System.out.println("Updated book with ID " + id + ": New author is " + newAuthor);
        } else {
            System.out.println("No book found with ID " + id);
        }
    }

}

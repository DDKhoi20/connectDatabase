
import config.SpringConfig;
import entity2.BookDetailsEntity;
import entity2.BookEntity;
import entity2.CategoryEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository2.BookRepository;
import repository2.CategoryRepository;

import java.time.LocalDate;
import java.util.List;

public class Main2 {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = context.getBean(BookRepository.class);
    static CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

    public static void main(String[] args) {
        createNewBookEntryWithNewCategory();
        createNewBookEntry();
        findByAuthor("Roger");
        findByNameAndAuthor("Java", "Roger");
        findByNameOrAuthor("Java", "Roger");
        findByPriceLessThan(80);
        findByBookDetailsIsbn("ISBN1219321");
        findByNameContaining("Java");
        findBookNameStartWithUsingQuery("Java");
        findBookPriceGreaterThan(60);
    }

    public static void findByAuthor(String author) {
        List<BookEntity> bookEntityList = bookRepository.findByAuthor(author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which author = " + author);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameAndAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameAndAuthor(name, author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which name = " + name + " and author = " + author);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameOrAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameOrAuthor(name, author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which name = " + name + " or author = " + author);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByPriceLessThan(int price) {
        List<BookEntity> bookEntityList = bookRepository.findByBookDetailsPriceLessThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books with price less than " + price);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContaining(String name) {
        List<BookEntity> bookEntityList = bookRepository.findByNameContaining(name);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books containing name = " + name);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByBookDetailsIsbn(String isbn) {
        BookEntity bookEntity = bookRepository.findByBookDetailsIsbn(isbn);
        if (bookEntity != null) {
            System.out.println("\nFind book with ISBN = " + isbn);
            System.out.println(bookEntity.toString());
        }
    }

    public static void findBookNameStartWithUsingQuery(String name) {
        List<BookEntity> bookEntityList = bookRepository.getBookNameStartWith(name);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books where name starts with " + name);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findBookPriceGreaterThan(int price) {
        List<BookEntity> bookEntityList = bookRepository.getBookPriceGreaterThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books with price greater than " + price);
            for (BookEntity bookEntity : bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void createNewBookEntry() {
        // Assuming category with ID 1 exists
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);

        bookRepository.save(bookEntity);
    }

    public static void createNewBookEntryWithNewCategory() {
        CategoryEntity categoryEntity = createNewCategory();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);

        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createNewCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("B1 - B2");
        categoryEntity.setDescription("New English books");

        return categoryEntity;
    }

    private static BookEntity createNewBook() {
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setNumberOfPage(22);
        bookDetailsEntity.setPrice(64);
        bookDetailsEntity.setPublishDate(LocalDate.now());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Eng B1");
        bookEntity.setAuthor("Dang Khoi");
        bookEntity.setBookDetails(bookDetailsEntity);
        bookDetailsEntity.setBook(bookEntity);

        return bookEntity;
    }
}

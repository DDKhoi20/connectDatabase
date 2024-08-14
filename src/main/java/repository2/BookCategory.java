package repository2;


import entity2.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategory extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByAuthor(String author);

    List<BookEntity> findByNameAndAuthor(String name, String author);

    List<BookEntity> findByNameOrAuthor(String name, String author);

    BookEntity findByBookDetailsIsbn(String isbn);

    List<BookEntity> findByBookDetailsPriceLessThan(int price);

    List<BookEntity> findByNameContaining(String name);
}

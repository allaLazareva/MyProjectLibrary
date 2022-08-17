package boot.services.book.interf;

import boot.model.BookModel;

import java.util.List;

public interface BookServiceInterface {

    List<BookModel> findByName(String name);

    BookModel findById(Integer id);

    List<BookModel> findByAuthor(String author);

    List<BookModel> findAllAndSortedByAuthor();

    void save(BookModel bookModel);

    void update(Integer id, BookModel bookModel);
}

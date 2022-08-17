package boot.dao.book.impl;

import boot.model.BookModel;

import java.util.List;

public interface DAOBookInterface {

    BookModel findById(Integer id);

    List<BookModel> findByName(String name);

    List<BookModel> findByAuthor(String author);

    List<BookModel> findAll();

    void saveBook(BookModel bookModel);

    void updateBook(Integer id, BookModel bookModel);


    //  List<PersonModel> findPersonByBookName(String name);
}

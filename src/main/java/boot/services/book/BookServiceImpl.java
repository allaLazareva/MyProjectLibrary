package boot.services.book;

import boot.dao.book.impl.DAOBookInterface;

import boot.model.BookModel;

import boot.services.book.interf.BookServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceInterface {
    private final DAOBookInterface bookDAO;

    @Override
    public List<BookModel> findByName(String name) {
        return bookDAO.findByName(name);
    }

    @Override
    public BookModel findById(Integer id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<BookModel> findByAuthor(String author) {
        return bookDAO.findByAuthor(author);
    }

    @Override
    public List<BookModel> findAllAndSortedByAuthor() {
        return (bookDAO.findAll()).stream().sorted(Comparator.comparing(BookModel::getAuthor))
                .collect(Collectors.toList());

    }

    @Override
    public void save(BookModel bookModel) {
        bookDAO.saveBook(bookModel);
    }

    @Override
    public void update(Integer id, BookModel bookModel) {
        bookDAO.updateBook(id, bookModel);
    }

}





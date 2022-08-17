package boot.dao.book;

import boot.convert.interf.IConverter;
import boot.dao.book.impl.DAOBookInterface;
import boot.entity.BookEntity;
import boot.model.BookModel;
import boot.repositiries.BookEntityRepository;
import boot.util.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JPABookDAO implements DAOBookInterface {
    private final BookEntityRepository bookEntityRepository;
    private final IConverter<BookEntity, BookModel> converterModelToEntity;
    private final IConverter<BookModel, BookEntity> converterEntityToModel;

    @Override
    public BookModel findById(Integer id) {
        BookEntity bookEntity = bookEntityRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return converterEntityToModel.convert(bookEntity);
    }

    @Override
    public List<BookModel> findByName(String name) {
        List<BookEntity> bookEntityList = bookEntityRepository.findBookByName(name);

        return bookEntityList.stream()
                .map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookModel> findByAuthor(String author) {
        List<BookEntity> bookEntityList = bookEntityRepository.findBookEntitiesByAuthor(author);

        return bookEntityList.stream().map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookModel> findAll() {
        List<BookEntity> bookEntityList = bookEntityRepository.findAll();
        return bookEntityList.stream().map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void saveBook(BookModel bookModel) {
        BookEntity bookEntity = converterModelToEntity.convert(bookModel);
        bookEntityRepository.save(bookEntity);

    }

    @Override
    public void updateBook(Integer id, BookModel bookModel) {
        BookEntity bookEntity = bookEntityRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookEntity.setName(converterModelToEntity.convert(bookModel).getName());
        bookEntity.setAuthor(converterModelToEntity.convert(bookModel).getAuthor());
        bookEntity.setYear(converterModelToEntity.convert(bookModel).getYear());
        bookEntityRepository.save(bookEntity);

    }





}

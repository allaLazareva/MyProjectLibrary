package boot.convert.book;

import boot.convert.interf.IConverter;
import boot.entity.BookEntity;
import boot.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEntityToBookModel implements IConverter<BookModel, BookEntity> {

    @Override
    public BookModel convert(BookEntity bookEntity) {
        BookModel bookModel=new BookModel();
        bookModel.setId(bookEntity.getId());
        bookModel.setName(bookEntity.getName());
        bookModel.setAuthor(bookEntity.getAuthor());
        bookModel.setYear(bookEntity.getYear());
        return bookModel;
    }
}

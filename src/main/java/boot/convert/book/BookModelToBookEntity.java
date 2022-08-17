package boot.convert.book;

import boot.convert.interf.IConverter;
import boot.entity.BookEntity;
import boot.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookModelToBookEntity implements IConverter<BookEntity, BookModel> {


    @Override
    public BookEntity convert(BookModel bookModel) {
        BookEntity bookEntity=new BookEntity();
        bookEntity.setName(bookModel.getName());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setYear(bookModel.getYear());
        return bookEntity;
    }
}

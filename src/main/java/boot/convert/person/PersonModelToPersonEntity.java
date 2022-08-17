package boot.convert.person;

import boot.convert.interf.IConverter;
import boot.entity.BookEntity;
import boot.entity.PersonEntity;
import boot.model.BookModel;
import boot.model.PersonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonModelToPersonEntity implements IConverter<PersonEntity, PersonModel> {

    private final IConverter<BookEntity, BookModel> converter;

    @Override
    public PersonEntity convert(PersonModel personModel) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personModel.getName());
        personEntity.setYearOfBirth(personEntity.getYearOfBirth());
        List<BookModel> bookModelList = personModel.getBookModelList();
        personEntity.setBooks(bookModelList.stream()
                .map(converter::convert)
                .collect(Collectors.toList()));

        return personEntity;
    }
}

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
public class PersonEntityToPersonModel implements IConverter<PersonModel, PersonEntity> {
    private final IConverter<BookModel, BookEntity> converter;



    @Override
    public PersonModel convert(PersonEntity personEntity) {
        PersonModel personModel=new PersonModel();
        personModel.setId(personEntity.getId());
        personModel.setName(personEntity.getName());
        personModel.setYearOfBirth(personEntity.getYearOfBirth());
        List<BookEntity> bookEntityList = personEntity.getBooks();
        personModel.setBookModelList(bookEntityList.stream()
                .map(converter::convert)
                .collect(Collectors.toList()));

        return personModel;
    }


}

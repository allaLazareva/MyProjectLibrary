package boot.dao.person;

import boot.convert.interf.IConverter;
import boot.dao.person.impl.DAOPersonInterface;
import boot.entity.BookEntity;
import boot.entity.PersonEntity;
import boot.model.BookModel;
import boot.model.PersonModel;
import boot.repositiries.PersonEntityRepository;
import boot.util.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JPAPersonDAO implements DAOPersonInterface {
    private final PersonEntityRepository personEntityRepository;
    private final IConverter<PersonEntity, PersonModel> converterModelToEntity;
    private final IConverter<PersonModel, PersonEntity> converterEntityToModel;
    private final IConverter<BookEntity, BookModel> converterModelToEntity2;


    @Override
    public PersonModel findById(Integer id) {
      PersonEntity personEntity = personEntityRepository     //Optional??????
                .findById(id).orElseThrow(PersonNotFoundException::new);
        return converterEntityToModel.convert(personEntity);
    }

    @Override
    public List<PersonModel> findByName(String name) {
        List<PersonEntity> personEntityList = personEntityRepository.findPersonByName(name);
        return personEntityList.stream()
                .map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }


    @Override
    public List<PersonModel> findAll() {
        List<PersonEntity> personEntityList = personEntityRepository.findAll();
        return personEntityList.stream()
                .map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonModel> findPersonByBooksName(String name) {
        List<PersonEntity> personEntityList = personEntityRepository.findPersonEntitiesByBooksName(name);
        return personEntityList.stream()
                .map(converterEntityToModel::convert)
                .collect(Collectors.toList());
    }


    @Override
    public void save(PersonModel personModel) {
        personEntityRepository.save(converterModelToEntity.convert(personModel));

    }

    @Override
    public void updatePerson(Integer id, PersonModel personModel) {
        PersonEntity personEntity = personEntityRepository
                .findById(id).orElseThrow(PersonNotFoundException::new);
        personEntity.setName(personModel.getName());
        personEntity.setYearOfBirth(personModel.getYearOfBirth());
        List<BookModel> bookModelList = personModel.getBookModelList();
        personEntity.setBooks(bookModelList.stream()
                .map(converterModelToEntity2::convert)
                .collect(Collectors.toList()));
        personEntityRepository.save(personEntity);

    }





}

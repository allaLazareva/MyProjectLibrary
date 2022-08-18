package boot.services.person.interf;

import boot.model.PersonModel;

import java.util.List;

public interface PersonServiceInterface {
    PersonModel findById(Integer id);

    List<PersonModel> findPersonByName(String name);

    List<PersonModel> findAllAndSortByName();

    void save(PersonModel personModel);

    void updatePerson(Integer id, PersonModel personModel);

    List<PersonModel> findPersonByBooksName(String name);

    void deletePersonById(Integer id);

}

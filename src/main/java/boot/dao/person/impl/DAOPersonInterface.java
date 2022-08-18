package boot.dao.person.impl;

import boot.model.PersonModel;

import java.util.List;

public interface DAOPersonInterface {

    PersonModel findById(Integer id); //id unique

    List<PersonModel> findByName(String name);// name unique

    List<PersonModel> findAll();

    void save(PersonModel personModel); ///??????????????возвращаемый тип?????????????????

    void updatePerson(Integer id, PersonModel personModel);

    List<PersonModel> findPersonByBooksName(String name);

     void deletePersonById(Integer id);

     void deletePerson(PersonModel personModel);
}

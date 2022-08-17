package boot.services.person;

import boot.dao.person.impl.DAOPersonInterface;
import boot.model.PersonModel;
import boot.services.person.interf.PersonServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonServiceInterface {

    private final DAOPersonInterface personDAO;

    @Override
    public PersonModel findById(Integer id) {
        return personDAO.findById(id);
    }

    @Override
    public List<PersonModel> findPersonByName(String name) {
        return personDAO.findByName(name);
    }

    @Override
    public List<PersonModel> findAllAndSortByName() {
        List<PersonModel> personModelList = personDAO.findAll();
        return personModelList.stream()
                .sorted(Comparator.comparing(PersonModel::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonModel> findPersonByBooksName(String name) {
        List<PersonModel> personModelList = personDAO.findPersonByBooksName(name);
        return personModelList;
    }

    @Override
    public void save(PersonModel personModel) {
        personDAO.save(personModel);

    }

    @Override
    public void updatePerson(Integer id, PersonModel personModel) {
        personDAO.updatePerson(id, personModel);

    }

}


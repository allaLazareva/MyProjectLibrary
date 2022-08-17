package boot.repositiries;

import boot.entity.PersonEntity;
import boot.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Integer> {
    @Query("select p from PersonEntity p where p.name = ?1")
    List<PersonEntity> findPersonByName(String name);

    @Query("select p from PersonEntity p inner join p.books books where books.name = ?1")
    List<PersonEntity> findPersonEntitiesByBooksName(String name);

}

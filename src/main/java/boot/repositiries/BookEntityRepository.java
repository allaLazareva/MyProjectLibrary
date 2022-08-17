package boot.repositiries;

import boot.entity.BookEntity;
import boot.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findBookByName(String name);

    @Query("select b from BookEntity b where b.author = ?1")
    List<BookEntity> findBookEntitiesByAuthor(String name);

}

package boot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "year_of_birth")
    @Min(value = 0)
    private Integer yearOfBirth;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Person_Book", joinColumns = {@JoinColumn(name = "Person_id")},
            inverseJoinColumns = {@JoinColumn(name = "Book_id")}
    )
@Fetch(FetchMode.SUBSELECT) //@Fetch(FetchMode.JOIN) dont work
    private List<BookEntity> books;

    public PersonEntity(String name) {
        this.name = name;
    }

    public PersonEntity(int id) {
        this.id = id;
    }

    public PersonEntity(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public PersonEntity(String name, int yearOfBirth, List<BookEntity> books) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity person = (PersonEntity) o;
        return id == person.id && yearOfBirth == person.yearOfBirth && Objects.equals(name, person.name) && Objects.equals(books, person.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfBirth, books);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

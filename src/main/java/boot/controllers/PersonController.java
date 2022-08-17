package boot.controllers;

import boot.entity.PersonEntity;
import boot.model.PersonModel;
import boot.services.person.interf.PersonServiceInterface;
import boot.util.ErrorResponse;
import boot.util.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonServiceInterface personService;


    @ResponseBody
    @GetMapping("/id/{id}")
    public ResponseEntity<PersonModel> findPersonById(@PathVariable("id") Integer id) {

        PersonModel personModel = personService.findById(id);
        return new ResponseEntity<>(personModel, HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonModel>> findPersonByName(@PathVariable("name") String nameOfPerson) {
        List<PersonModel> personModelList = personService.findPersonByName(nameOfPerson);

        return new ResponseEntity<>(personModelList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<PersonModel>> findAllAndSortByName() {
        List<PersonModel> personModelList = personService.findAllAndSortByName();
        return new ResponseEntity<>(personModelList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/book/{book}")
    public ResponseEntity<List<PersonModel>> findPersonByBookName(@PathVariable("book") String book) {
        List<PersonModel> personModelList = personService.findPersonByBooksName(book);
        return new ResponseEntity<>(personModelList, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<PersonEntity> save(@RequestBody PersonModel personModel) {
        personService.save(personModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable("id") Integer id,
                                          @RequestBody PersonModel personModel) {
        personService.updatePerson(id, personModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(PersonNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Person " +
                "with this id was not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}















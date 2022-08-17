package boot.controllers;

import boot.entity.PersonEntity;
import boot.model.BookModel;
import boot.services.book.interf.BookServiceInterface;
import boot.util.BookNotFoundException;
import boot.util.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceInterface booksService;


    @GetMapping("/name/{name}")
    @ResponseBody
    public ResponseEntity<List<BookModel>> findByName(@PathVariable("name") String name) {
        List<BookModel> bookModelList = booksService.findByName(name);
        return new ResponseEntity<>(bookModelList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<BookModel> findById(@PathVariable("id") Integer id) {
        BookModel bookModel = booksService.findById(id);
        return new ResponseEntity<>(bookModel, HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    @ResponseBody
    public ResponseEntity<List<BookModel>> findByAuthor(@PathVariable("author") String author) {
        List<BookModel> bookModelList = booksService.findByAuthor(author);
        return new ResponseEntity<>(bookModelList, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<BookModel>> findAllAndSortedByAuthor() {
        List<BookModel> bookModelList = booksService.findAllAndSortedByAuthor();
        return new ResponseEntity<>(bookModelList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonEntity> save(@RequestBody BookModel bookModel) {
        booksService.save(bookModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<PersonEntity> update(@PathVariable("id") Integer id, @RequestBody BookModel bookModel) {
        booksService.update(id, bookModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(BookNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Book " +
                "with this id was not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }


}








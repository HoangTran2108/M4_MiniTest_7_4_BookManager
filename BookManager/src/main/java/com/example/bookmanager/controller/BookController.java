package com.example.bookmanager.controller;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> showAllBook() {
        return new ResponseEntity<>((List<Book>) bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) Long minPrice,
                                  @RequestParam(required = false) Long maxPrice) {
        if (name == null && author == null && minPrice == null && maxPrice == null) {
            return new ResponseEntity<>((List<Book>) bookService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookService.findAllByNameContainingAndAuthorContainingAndPriceBetween(
                    name != null ? name : "",
                    author != null ? author : "",
                    minPrice != null ? minPrice : 0L,
                    maxPrice != null ? maxPrice : Long.MAX_VALUE), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.remove(id);
        return new ResponseEntity<>(book.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book book){
        Optional<Book>books = bookService.findById(id);
        if(!books.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(books.get().getId());
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Long> getTotalPrice() {
        Long totalPrice = bookService.getTotalPrice();
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }


}

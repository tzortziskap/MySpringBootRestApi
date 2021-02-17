/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.controller;

import emergon.entity.Book;
import emergon.repository.BookRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @book tzortziskapellas
 */
@RestController
//@Controller
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

//    @ResponseBody
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int id) {
        return new ResponseEntity(bookRepo.findById(id).get(), HttpStatus.OK);
    }
    
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepo.save(book);
    }
    
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable int id){
       Book bookToUpdate = bookRepo.findById(id).get();
       bookToUpdate.setTitle(book.getTitle());
        return new ResponseEntity(bookToUpdate, HttpStatus.OK);
    }
    
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> delete(@PathVariable (value = "id") int id){
        bookRepo.deleteById(id);
        String message = "The book with id = " + id + " is deleted";
        return ResponseEntity.ok(message);
    }
    
        @GetMapping("/authors/{authorId}/books")
        public List<Book> getBooksByAuthor(@PathVariable(value = "authorId") int authorId) {
        return bookRepo.findByAuthorId(authorId);
    }
}

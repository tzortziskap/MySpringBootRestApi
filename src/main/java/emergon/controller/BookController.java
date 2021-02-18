/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.controller;

import emergon.entity.Book;
import emergon.repository.AuthorRepo;
import emergon.repository.BookRepo;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

//    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int id) {
        return new ResponseEntity(bookRepo.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable int id) {
        Book bookToUpdate = bookRepo.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        return new ResponseEntity(bookToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") int id) {
        bookRepo.deleteById(id);
        String message = "The book with id = " + id + " is deleted";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/authors/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable(value = "authorId") int authorId) {
        return bookRepo.findByAuthorId(authorId);
    }

    @PutMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Book> updateBooksByAuthors(@PathVariable int authorId, @PathVariable int bookId, @RequestBody Book book) {
        if (!authorRepo.existsById(authorId)) {
            return ResponseEntity.notFound().build();
        }
        Book updateBook = bookRepo.findById(bookId).get();
        updateBook.setTitle(book.getTitle());
        updateBook = bookRepo.save(updateBook);
        return ResponseEntity.ok(updateBook);
    }
    
    @DeleteMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<String> deleteBookOfAuthor(@PathVariable int authorId, @PathVariable int bookId){
        
        Optional<Book> optionalBook = bookRepo.findByIdAndAuthorId(bookId, authorId);
        if (optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }else{
            bookRepo.delete(optionalBook.get());
             return ResponseEntity.ok("Deleted");
        }
       
    }
    }

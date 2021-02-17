/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.controller;

import emergon.entity.Author;
import emergon.repository.AuthorRepo;
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
 * @author tzortziskapellas
 */
@RestController
//@Controller
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping
    public List<Author> findAll() {
        return authorRepo.findAll();
    }

//    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") int id) {
        return new ResponseEntity(authorRepo.findById(id).get(), HttpStatus.OK);
    }
    
    @PostMapping
    public Author create(@RequestBody Author author){
        return authorRepo.save(author);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@RequestBody Author author, @PathVariable int id){
       Author authorToUpdate = authorRepo.findById(id).get();
       authorToUpdate.setName(author.getName());
        return new ResponseEntity(authorToUpdate, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable (value = "id") int id){
        authorRepo.deleteById(id);
        String message = "The author with id = " + id + " is deleted";
        return ResponseEntity.ok(message);
    }
}

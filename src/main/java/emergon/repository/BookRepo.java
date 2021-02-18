/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.repository;

import emergon.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tzortziskapellas
 */
@Repository
public interface BookRepo extends JpaRepository<Book,Integer>{

    List<Book> findByAuthorId(int authorId);
    Optional<Book> findByIdAndAuthorId(int bookId, int authorId);
    
}

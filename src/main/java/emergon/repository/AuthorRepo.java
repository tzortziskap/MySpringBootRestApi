/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.repository;

import emergon.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tzortziskapellas
 */
@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer>{
    
}

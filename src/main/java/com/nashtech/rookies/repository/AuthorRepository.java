package com.nashtech.rookies.repository;

import java.util.List;
import java.util.Optional;

import com.nashtech.rookies.model.Author;

public interface AuthorRepository 	{
    
	Optional<Author> save	(Author author);

    Optional<Author> findById(Integer id);
    
    List<Author> findAll();
    
    Optional<Author> findByName(String name);

}

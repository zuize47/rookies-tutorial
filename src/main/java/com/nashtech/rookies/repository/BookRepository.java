package com.nashtech.rookies.repository;

import com.nashtech.rookies.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
	
    public Optional<Book> save(Book book);

    public Optional<Book> findById(Integer id);
    
    public List<Book> findAll();

    public Optional<Book> findByName(String name);

    public Optional<Book> findByNameNamedQuery(String name);
}

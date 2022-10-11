package com.nashtech.rookies.repository.spring;

import java.util.Optional;

import com.nashtech.rookies.model.Book;

public interface CustomizedBookRepository {
	
	Optional<Book> customize(Integer id);

}

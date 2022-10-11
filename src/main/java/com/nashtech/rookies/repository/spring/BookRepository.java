package com.nashtech.rookies.repository.spring;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nashtech.rookies.dto.BookDetail;
import com.nashtech.rookies.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, CustomizedBookRepository {

	List<Book> findByName(String name);
	
	Optional<BookDetail> findOneById(Integer id);
	
	@Query("SELECT u FROM Book u WHERE u.status = 1")
	Collection<Book> findAllActiveBooks();
}


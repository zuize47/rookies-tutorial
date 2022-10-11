package com.nashtech.rookies.repository.spring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.rookies.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	List<Author> findByName(String name);

}

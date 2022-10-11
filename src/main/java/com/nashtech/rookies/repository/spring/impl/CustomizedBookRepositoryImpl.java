package com.nashtech.rookies.repository.spring.impl;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.nashtech.rookies.model.Book;
import com.nashtech.rookies.repository.spring.CustomizedBookRepository;

public class CustomizedBookRepositoryImpl implements CustomizedBookRepository {
	
	private final EntityManager entityManager;

	public CustomizedBookRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Book> customize(Integer id) {
		return Optional.ofNullable(entityManager.find(Book.class, id));
	}

}

package com.nashtech.rookies.repository.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.nashtech.rookies.model.Author;
import com.nashtech.rookies.repository.AuthorRepository;

public class AuthorRepositoryImpl implements AuthorRepository {

	private EntityManager entityManager;

	public AuthorRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Author> save(Author author) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			return Optional.of(author);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Author> findById(Integer id) {
		Author author = entityManager.find(Author.class, id);
		return author != null ? Optional.of(author) : Optional.empty();
	}

	public List<Author> findAll() {
		return entityManager.createQuery("from Author", Author.class).getResultList();
	}

	public Optional<Author> findByName(String name) {
		List<Author> authors = entityManager.createNamedQuery("Author.findByName", Author.class)
				.setParameter("name", name).getResultList();

		return authors.size() > 0 ? Optional.of(authors.get(0)) : Optional.empty();
	}
}

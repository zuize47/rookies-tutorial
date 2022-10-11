package com.nashtech.rookies.repository.hibernate;

import java.util.List;
import java.util.Optional;

import com.nashtech.rookies.model.Author;
import com.nashtech.rookies.repository.AuthorRepository;
import com.nashtech.rookies.utils.HibernateUtil;

public class AuthorRepositoryImpl implements AuthorRepository {

	public AuthorRepositoryImpl() {
	}

	@Override
	public Optional<Author> save(Author author) {
		return HibernateUtil.doInSession(session -> {
			session.saveOrUpdate(author);
			return Optional.of(author);
		});

	}

	@Override
	public Optional<Author> findById(Integer id) {
		return HibernateUtil.doInSession(session -> Optional.of(session.get(Author.class, id)));
	}

	@Override
	public List<Author> findAll() {
		return HibernateUtil
				.doInSession(session -> session.createQuery("FROM Author", Author.class).getResultList());
	}

	@Override
	public Optional<Author> findByName(String name) {
		return HibernateUtil
				.doInSession(session -> 
					Optional.ofNullable(
							session.createQuery("FROM Author a WHERE lower(a.name) like lower(:name) ", Author.class)
							.setParameter("name", "%" + name + "%")
							.getSingleResult())
				
				);
		
	}

}

package com.nashtech.rookies.repository.hibernate;

import java.util.List;
import java.util.Optional;

import com.nashtech.rookies.model.Book;
import com.nashtech.rookies.repository.BookRepository;
import com.nashtech.rookies.utils.HibernateUtil;

public class BookRepositoryImpl implements BookRepository {

	public BookRepositoryImpl() {

	}

	@Override
	public Optional<Book> save(Book book) {
		return HibernateUtil.doInSession(session -> {
			session.saveOrUpdate(book);;
			return Optional.of(book);
		});
	}

	@Override
	public Optional<Book> findById(Integer id) {
		return HibernateUtil.doInSession(session -> Optional.ofNullable(session.get(Book.class, id)));
	}

	@Override
	public List<Book> findAll() {
		return HibernateUtil.doInSession(session -> session.createQuery("FROM Book", Book.class).getResultList());
	}

	@Override
	public Optional<Book> findByName(String name) {
		return HibernateUtil.doInSession(session -> {
			var result = session.createQuery("FROM Book b WHERE lower(b.name) like lower(:name) ", Book.class)
					.setParameter("name", "%" + name + "%").getResultList();
			if (result.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(result.get(0));

		});
	}

	@Override
	public Optional<Book> findByNameNamedQuery(String name) {
		return this.findByName(name);
	}

}

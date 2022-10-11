package com.nashtech.rookies;

import com.nashtech.rookies.model.Author;
import com.nashtech.rookies.model.Book;
import com.nashtech.rookies.repository.AuthorRepository;
import com.nashtech.rookies.repository.BookRepository;
import com.nashtech.rookies.repository.jpa.BookRepositoryImpl;
import com.nashtech.rookies.repository.jpa.AuthorRepositoryImpl;
import com.nashtech.rookies.utils.JpaEntityManagerFactory;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public class JpaExample {

    public static void main(String[] args) {
		
        final JpaEntityManagerFactory jpaEntityManagerFactory = new JpaEntityManagerFactory(
        		List.of(Book.class, Author.class).toArray(Class[]::new));
        final EntityManager entityManager = jpaEntityManagerFactory.getEntityManager();

        // Create our repositories
        BookRepository bookRepository = new BookRepositoryImpl(entityManager);
        AuthorRepository authorRepository = new AuthorRepositoryImpl(entityManager);

        // Create an author and add 3 books to his list of books
        Author author = new Author("Author 1");
        author.addBook(new Book("Book 1", "code1"));
        author.addBook(new Book("Book 2", "code2"));
        author.addBook(new Book("Book 3", "code3"));
        Optional<Author> savedAuthor = authorRepository.save(author);
        savedAuthor.stream().forEach(System.out::println);
        
        author = new Author("Author 2");
        author.addBook(new Book("Book 1a", "code_1a"));
        author.addBook(new Book("Book 2a", "code_2a"));
        authorRepository.save(author);
//        System.out.println("Saved author: " + savedAuthor.get());

        // Find all authors
        List<Author> authors = authorRepository.findAll();
        System.out.println("Authors:");
        authors.forEach(System.out::println);

        // Find author by name
        Optional<Author> authorByName = authorRepository.findByName("Author 2");
        System.out.println("Searching for an author by name: ");
        authorByName.ifPresent(System.out::println);

        // Search for a book by ID
        Optional<Book> foundBook = bookRepository.findById(2);
        foundBook.ifPresent(System.out::println);

        // Search for a book with an invalid ID
        Optional<Book> notFoundBook = bookRepository.findById(99);
        notFoundBook.ifPresent(System.out::println);

        // List all books
        List<Book> books = bookRepository.findAll();
        System.out.println("Books in database:");
        books.forEach(System.out::println);

        // Find a book by name
        Optional<Book> queryBook1 = bookRepository.findByName("Book 2");
        System.out.println("Query for book 2:");
        queryBook1.ifPresent(System.out::println);

        // Find a book by name using a named query
        Optional<Book> queryBook2 = bookRepository.findByNameNamedQuery("Book 3");
        System.out.println("Query for book 3:");
        queryBook2.ifPresent(System.out::println);

        // Add a book to author 1
        Optional<Author> author1 = authorRepository.findById(1);
        author1.ifPresent(a -> {
        	a.addBook(new Book("Book 4", "code 3"));
            System.out.println("Saved author: " + authorRepository.save(a));
        });

        // Close the entity manager and associated factory
        entityManager.close();
        jpaEntityManagerFactory.close();
        

    }
}

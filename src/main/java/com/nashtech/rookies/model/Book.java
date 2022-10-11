package com.nashtech.rookies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BOOK", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
@NamedQueries({ @NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.name = :name"),
		@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b") })
public class Book {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Column(name = "NUM_OF_PAGES")
	private Integer numOfPage;
	private String coverUrl;
	private String code;
	private int status = 1;
	

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Author author;

	public Book() {
	}

	public Book(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Book(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getNumOfPage() {
		return numOfPage;
	}

	public void setNumOfPage(Integer numOfPage) {
		this.numOfPage = numOfPage;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author=" + author.getName() + '}';
	}
}

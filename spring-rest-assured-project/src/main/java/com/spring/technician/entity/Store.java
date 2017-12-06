package com.spring.technician.entity;

import java.util.ArrayList;
import java.util.List;

public class Store {

	List<Book> book = new ArrayList<>();

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}
	
	
}

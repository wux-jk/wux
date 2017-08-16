package com.jk.service.book;

import java.util.List;

import com.jk.pojo.book.Book;

public interface BookService {

	List selectBookList();

	

	Book selectBookById(Integer bookID);

	void updateBookById(Book book);

	void insertBookInFo(Book book);



	void deleteBookById(Integer bookID);

}

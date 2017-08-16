package com.jk.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.book.BookDao;
import com.jk.pojo.book.Book;

@Service
public class BookServiceImpl implements BookService {

	 @Autowired
	  private BookDao bookDao;
	
	
	@Override
	public List<Book> selectBookList() {
		 return bookDao.selectBookList();
	}


	

	@Override
	public Book selectBookById(Integer bookID) {
		
		return bookDao.selectBookById(bookID);
	}


	@Override
	public void updateBookById(Book book) {
		bookDao.updateBookById(book);
		
	}


	@Override
	public void insertBookInFo(Book book) {
		bookDao.insertBookInFo(book);
		
	}




	@Override
	public void deleteBookById(Integer bookID) {
		bookDao.deleteBookById(bookID);
		
	}

}

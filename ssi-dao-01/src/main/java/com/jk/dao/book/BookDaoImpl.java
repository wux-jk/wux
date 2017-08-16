package com.jk.dao.book;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.pojo.book.Book;

@Repository
public class BookDaoImpl extends SqlMapClientDaoSupport implements BookDao {

	/**
	 * <pre>selectBookList(查询所有信息功能，)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:27:56    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:27:56    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@Override
	public List<Book> selectBookList() {
														//这个方法和book.xml的ID对应着，和action层maping的方法没有关系
		return getSqlMapClientTemplate().queryForList("book.selectBookList");
	}

	
	/**
	 * <pre>selectBookById(查询ID，修改做回显功能)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:28:59    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:28:59    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@Override
	public Book selectBookById(Integer bookID) {
		
		return (Book) getSqlMapClientTemplate().queryForList("book.selectBookById",bookID).get(0);
	}

	/**
	 * <pre>updateBookById(根据ID 做修改功能)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:30:32    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:30:32    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@Override
	public void updateBookById(Book book) {
		getSqlMapClientTemplate().update("book.updateBookById",book);
		
	}


	/**
	 * <pre>insertBookInFo(新增功能)  
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:31:29    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:31:29    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@Override
	public void insertBookInFo(Book book) {
		getSqlMapClientTemplate().insert("book.insertBook",book);
		
	}


	/**
	 * <pre>deleteBookById(根据ID 删除信息功能)  
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:31:29    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:31:29    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@Override
	public void deleteBookById(Integer bookID) {
		getSqlMapClientTemplate().delete("book.deleteBookById",bookID);
	}

}

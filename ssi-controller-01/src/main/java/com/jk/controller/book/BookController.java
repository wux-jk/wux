package com.jk.controller.book;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jk.pojo.book.Book;
import com.jk.service.book.BookService;


@Controller
public class BookController {
	
	
	  @Resource
	  private BookService bookService;
	
	  private Book book;
	  
	 /* private File upImg;
	  private String upImgFileName;*/
	
	/**
	 * <pre>selectBookList(查询所有信息功能,是前台请求的方法，和dao层那个方法没有关系)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:27:56    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:27:56    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	  /*@RequestMapping("selectBookList")
	  public ModelAndView selectBookList(Book book) {
	    List boList = bookService.selectBookList();
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("dataList", boList);
	    mv.setViewName("list");
	    return mv;
	} */
	
	/**
	 * <pre>selectBookListJson(将查询结果转成json返回到前台)   
	 * Founder：吴茜     
	 * Found_time：2017年7月24日 下午2:45:56    
	 * Updater：吴茜       
	 * Update_Time：2017年7月24日 下午2:45:56    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	 @RequestMapping("selectBookListJson")
	 @ResponseBody
	 List<Book> selectBookListJson(){
		 List<Book> boList=bookService.selectBookList();
		return boList;
		 
	 }
	
	
	/**
	 * <pre>toAddOrUpdate(跳转到新增或修改页面，修改做回显功能)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:28:59    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:28:59    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 */
	@RequestMapping("toAddOrUpdate")
	public ModelAndView toAddOrUpdate(Book book){
		 ModelAndView mv = new ModelAndView();
		if(book!=null && book.getBookID()!=null && !book.getBookID().equals("")) {
			book = bookService.selectBookById(book.getBookID());
			mv.addObject("book", book);
		}
		
		 mv.setViewName("addOrUpdate");
		 return mv;
	}
	
	
	/**
	 * <pre>addOrUpdate(如果有ID，是修改功能，没有ID，是新增功能)   
	 * Founder：吴茜     
	 * Found_time：2017年7月15日 上午8:30:32    
	 * Updater：吴茜       
	 * Update_Time：2017年7月15日 上午8:30:32    
	 * Update_Remark： 
	 * @param book
	 * @return</pre>
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping({"addOrUpdate"})
	public String addOrUpdate(Book book,HttpServletRequest request) throws IllegalStateException, IOException{
		String url="";
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
       CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
               request.getSession().getServletContext());
       //检查form中是否有enctype="multipart/form-data"
       if(multipartResolver.isMultipart(request))
       {
           //将request变成多部分request
           MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
          //获取multiRequest 中所有的文件名
           Iterator iter=multiRequest.getFileNames();
           
           while(iter.hasNext())
           {
               //一次遍历所有文件
               MultipartFile file=multiRequest.getFile(iter.next().toString());
               //判断是否有文件上传
               if(file!=null && file.getSize()>0)
               {
               	String path = request.getSession().getServletContext().getRealPath("/"+"img");
               	//判断要写入的文件夹是否存在
       			File test = new File(path);
       			if(!test.exists()){ //判断文件是否存在
       				test.mkdir(); //根据文件的路径  创建文件夹
       			  }
       			//重命名文件名  保证文件的唯一性
       				String newFileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
                   	url="/img/"+newFileName;
                       //上传的路径
                       file.transferTo(new File(path+"/"+newFileName));
                }
           }
          
       }
       
       
       
       
       
		
		
		if (book.getBookID() != null && !book.getBookID().equals("")) {
			if (!url.equals("")) {
				 book.setBookImg(url);
			}
			bookService.updateBookById(book);
		}else {
			 book.setBookImg(url);
			bookService.insertBookInFo(book);
		}
		
		 return "redirect:selectBookList.jhtml";
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
	@RequestMapping({"deleteBookById"})
	public String deleteBookById(Book book){
		bookService.deleteBookById(book.getBookID());
		 return "redirect:selectBookList.jhtml";
	}

	
	@RequestMapping("toBookListPage")
	String toBookListPage(){
		return "/dataList";
		
	}

	//-------------------------------------------------------
	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	
	
	

}

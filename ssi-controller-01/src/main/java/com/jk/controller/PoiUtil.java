package com.jk.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jk.pojo.UserReponse;



public class PoiUtil {
	
	/**
	 * <pre>exportExcle(拼接sheet页  根据当前数据创建sheet页)   
	 * Founder：吴茜     
	 * Found_time：2017年8月2日 下午2:22:45    
	 * Updater：吴茜       
	 * Update_Time：2017年8月2日 下午2:22:45    
	 * Update_Remark： 
	 * @param <UserReponse>
	 * @param 
	 * @param wb		当前excle文件xssfWorkBook 对象
	 * @param cloumNames	excle文件的第一列
	 * @param page</pre>	分页工具类
	 * @throws IOException 
	 */
	public static  void exportExcle(List<UserReponse> user) throws IOException{
			XSSFWorkbook xs=new XSSFWorkbook();
			//创建sheet页
			XSSFSheet sheet = xs.createSheet();
			
			Iterator<UserReponse> iterator = user.iterator();
			int i=0;
			while (iterator.hasNext()) {
				//创建行
				XSSFRow row = sheet.createRow(i);
				UserReponse userReponse = (UserReponse) iterator.next();
				XSSFCell cell = row.createCell(0);
				XSSFCell cell2 = row.createCell(1);
				XSSFCell cell3 = row.createCell(2);
				XSSFCell cell4 = row.createCell(3);
				
				cell.setCellValue(userReponse.getUserName());
				cell2.setCellValue(userReponse.getUserSex());
				cell3.setCellValue(userReponse.getUserAge());
				cell4.setCellValue(userReponse.getUserPassword());
				i++;
			}
			
			
				OutputStream os=new FileOutputStream("e:test.xlsx");
				xs.write(os);
			
	}

}

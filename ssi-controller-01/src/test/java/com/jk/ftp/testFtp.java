package com.jk.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;


public class testFtp {
	

	public void conFtp(){
		//实例化客户端
		FTPClient ftp=new FTPClient();
		try {
			//连接ftp地址
			ftp.connect("192.168.1.116", 21);
			//登录ftp服务器
			boolean login = ftp.login("root", "root");
			if(login){
				//如果密码正确  切换到根目录下
				ftp.changeWorkingDirectory("/");
				boolean b = ftp.changeWorkingDirectory("yang/test");
				if(!b){
					//如果没有 则创建一个路径
					ftp.makeDirectory("yang/test");
					
				}
				//如果上传媒体文件，需要设置二进制
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				
				//InputStream is=new FileInputStream("f:/杨大姐.txt");//自己的文件名
				InputStream f=new FileInputStream("f:/3.jpg");
				//5、向ftp上传文件								要上传以后的文件名
				boolean storeFile = ftp.storeFile(new String("杨大姐.jpg".getBytes("GBK"), "ISO-8859-1"), f);
				System.out.println(storeFile);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

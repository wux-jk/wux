package com.jk.controller.test;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class testSax {
	
	public static void main(String[] args) {
		 SAXParser parser = null; 
		 
         try {
        	//构建SAXParser  
			parser = SAXParserFactory.newInstance().newSAXParser();
			//实例化  DefaultHandler对象  
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

}

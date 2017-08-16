package com.jk.controller.test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class testDom {
	public static void main(String[] args) {
		SAXReader sax=new SAXReader();
		try {
			Document document = sax.read(new File("E:/workspace/parent-ssi-01/ssi-controller-01/src/main/resources/user.xml"));
			//通过document多想获取当前xml根标签
			Element rootElement = document.getRootElement();
			// 通过document对象获取根节点users
			System.out.println("根节点名："+rootElement.getName());
			//通过element对象的elementIterator方法获取迭代器
			Iterator it = rootElement.elementIterator();
			 // 遍历迭代器，获取根节点中的信息（用户）
			while (it.hasNext()) {
				System.out.println("开始遍历子节点");
				Element user = (Element) it.next();
				System.out.println("子节点名--"+user.getName());
				 // 获取user的属性名以及 属性值
				List<Attribute> list = user.attributes();
				for (Attribute attr : list) {
					System.out.println("属性名" + attr.getName()+ "----/" + "属性值---" + attr.getValue());
				}
				
				Iterator itt = rootElement.elementIterator();
				while (itt.hasNext()) {
					Element userChild = (Element) itt.next();
					System.out.println("节点名-"+ userChild.getName()+"-/" 
					+ "节点值--" + userChild.getStringValue());
					
				}
				
				System.out.println("遍历结束");
			}
			
			
			//获取子标签
			//Element userNmae = rootElement.element("userName");
			//把document对象装换成一个xml字符串 全部输出
			//String asXML = rootElement.asXML();
			
			//获取所有标签为user的子标签
			/*List<Element> list = rootElement.elements();
			for (Element element : list) {
				System.out.println("ID号：" + element.attributeValue("userId"));
				System.out.println( "姓名：" + element.elementText("userName"));
			}*/
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	

}

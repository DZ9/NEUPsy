package com.idoxie.util.word;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.idoxie.dao.IUserDAO;
import com.idoxie.dao.impl.UserDAO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MusicDocumentHandler {
	private Configuration configuration = null;

	public MusicDocumentHandler() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}

	public void createDoc(String name, String sex, String birthday,
			 String interest, String phone, String college, String grade, String stuNum,String nation,
			 String hometown, String advice, String other, String symptom,String rDate) {
		//要填入模本的数据文件
		Map<String,Object> dataMap=new HashMap<String,Object>();
		getData( dataMap,name, sex, birthday,
				  interest,  phone,  college,  grade,  stuNum, nation,
				  hometown,  advice,  other,  symptom, rDate);
//		getData(dataMap, name, stuNum, college,
//				 telephone, reserveTime, way, aspect, reTeacher, aTime,
//				 receiver, remark, result, aTeacher);
		//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		//这里我们的模板是放在com.havenliu.document.template包下面
		configuration.setClassForTemplateLoading(this.getClass(), "/com/template");
		Template t=null;
		try {
			//test.ftl为要装载的模板
			System.out.println(1);
			t = configuration.getTemplate("test3.ftl","utf-8");
			System.out.println(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出文档路径及名称
//		File outFile = new File("D:/temp/outFile.docx");
//		Writer out = null;
		  
		try {
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
//			PrintWriter out = new PrintWriter(new BufferedWriter(
//		            new FileWriter("D:/apache-tomcat-7.0.42/webapps/NEUPsy/"+stuNum+" "+rDate+".doc")));
			PrintWriter out = new PrintWriter(  
		            new OutputStreamWriter(  
		                new FileOutputStream("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/NEUPsy/"+"music.doc"),  
		                "UTF-8"));  
			t.process(dataMap, out);
			out.flush();
			out.close();
			System.out.println("Done");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
      
	}
	
	/**
	 * 注意dataMap里存放的数据Key值要与模板中的参数相对应
	 * @param dataMap
	 */
	 private void getData(Map<String,Object> dataMap, String name, String sex, String birthday,
			 String interest, String phone, String college, String grade, String stuNum,String nation,
			 String hometown, String advice, String other, String symptom,String rDate)
	  {
		 
		 
		  IUserDAO userDAO = new UserDAO();
		  setValue(dataMap, "name", name);
		  setValue(dataMap, "sex", sex);
		  setValue(dataMap, "birthday", birthday);
		  setValue(dataMap, "interest", interest);
		  setValue(dataMap, "phone", phone);
		  setValue(dataMap, "college", college);
		  setValue(dataMap, "grade", grade);
		  setValue(dataMap, "stuNum", stuNum);
		  setValue(dataMap, "nation", nation);
		  setValue(dataMap, "hometown", hometown);
		  setValue(dataMap, "advice", advice);
		  setValue(dataMap, "other", other);
		  setValue(dataMap, "year", rDate.substring(0,4));
		  setValue(dataMap, "month", rDate.substring(5,7));
		  setValue(dataMap, "day", rDate.substring(8,10));
		  
		 
		  
		 
		  if(symptom != null) {
			  
		  switch(symptom) {
		  case "神经紧张":
			  dataMap.put("sjjz", "√");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "压力过大":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "√");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "情绪不安 ":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "√");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "失眠焦虑":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "√");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "遇到突发意外事件":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "√");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "入睡困难":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "√");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			  break;
		  case "神经症":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "√");
			  dataMap.put("sjsr", "");
			  break;
		  case "神经衰弱":
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "√");
			  break;
		  default:
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
			 
		  	}
		  }else {
			  dataMap.put("sjjz", "");
			  dataMap.put("ylgd", "");
			  dataMap.put("qxba", "");
			  dataMap.put("smjl", "");
			  dataMap.put("tfsj", "");
			  dataMap.put("rskn", "");
			  dataMap.put("sjz", "");
			  dataMap.put("sjsr", "");
		  }
		 
		 
		  
//		  dataMap.put("author", "张三");
//		  dataMap.put("remark", "这是测试备注信息");
//		  List<Table1> _table1=new ArrayList<Table1>();
//		  
//		  Table1 t1=new Table1();
//		  t1.setDate("2010-10-1");
//		  t1.setText("制定10月开发计划内容。");
//		  _table1.add(t1);
//		  
//		  Table1 t2=new Table1();
//		  t2.setDate("2010-10-2");
//		  t2.setText("开会讨论开发计划");
//		  _table1.add(t2);
//		  
//		  dataMap.put("table1", _table1);
//		  
//		  
//		  List<Table2> _table2=new ArrayList<Table2>();
//		  for(int i=0;i<5;i++)
//		  {
//			  Table2 _t2=new Table2();
//			  _t2.setDetail("测试开发计划"+i);
//			  _t2.setPerson("张三——"+i);
//			  _t2.setBegindate("2010-10-1");
//			  _t2.setFinishdate("2010-10-31");
//			  _t2.setRemark("备注信息");
//			  _table2.add(_t2);
//		  }
//		  dataMap.put("table2", _table2);
		  
	  }
	  public void setValue(Map<String,Object> dataMap2,String key,String value) {
		  if(value != null) {
			  dataMap2.put(key, value);
		  }else {
			  dataMap2.put(key, "");
		  }
	  }
	

}

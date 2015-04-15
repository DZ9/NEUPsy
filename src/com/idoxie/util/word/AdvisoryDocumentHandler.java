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

public class AdvisoryDocumentHandler {
	private Configuration configuration = null;

	public AdvisoryDocumentHandler() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}

	public void createDoc(String name, String sex, String birthday,
			 String interest, String phone, String college, String grade, String stuNum,String nation,
			 String hometown,String content,String suggestion,String aspect,String major) {
		//要填入模本的数据文件
		Map<String,Object> dataMap=new HashMap<String,Object>();
		getData( dataMap,name, sex, birthday,
				  interest,  phone,  college,  grade,  stuNum, nation,
				  hometown,content,suggestion,aspect,major);
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
			t = configuration.getTemplate("zxk.ftl","utf-8");
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
            new FileOutputStream("F:/tomcat-7.0.57/webapps/NEUPsy-1.0.0/"+stuNum+" "+" advisory.doc"),  
            "UTF-8"));
//			PrintWriter out = new PrintWriter(  
//		            new OutputStreamWriter(  
//		                new FileOutputStream("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/NEUPsy/"+stuNum+" "+" advisory.doc"),  
//		                "UTF-8"));  
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
			 String hometown,String content,String suggestion,String aspect,String major)
	  {
		 
		 System.out.println(suggestion);
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
		  setValue(dataMap,"content",content);
		  setValue(dataMap,"suggestion",suggestion);
		  setValue(dataMap,"major",major);
		  switch(aspect) {
		  case "情绪、情感":
			  dataMap.put("emotion", "√");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");
			  break;
		  case "个性":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "√");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");	
			  break;
		  case "压力":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "√");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");
			  break;
		  case "神经质":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "√");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");
			  break;
		  case "人际关系":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "√");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");	
			  break;
		  case "学习、职业、生涯规划":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "√");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");	
			  break;
		  case "适应":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "√");
			  dataMap.put("other", "□");	
			  break;
		  case "其他":
			  dataMap.put("emotion", "□");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "√");
			  break;
		  default:
			  dataMap.put("emotion", "√");
			  dataMap.put("charecter", "□");
			  dataMap.put("pressure", "□");
			  dataMap.put("prissy", "□");
			  dataMap.put("connection", "□");
			  dataMap.put("study", "□");
			  dataMap.put("adjustability", "□");
			  dataMap.put("other", "□");
		  }
		  
	  }
		 
		  
		 
	  public void setValue(Map<String,Object> dataMap2,String key,String value) {
		  if(value != null) {
			  dataMap2.put(key, value);
		  }else {
			  dataMap2.put(key, "");
		  }
	  }
	

}

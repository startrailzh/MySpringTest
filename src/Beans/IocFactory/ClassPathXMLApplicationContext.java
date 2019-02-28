package Beans.IocFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.xml.xpath.XPathFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;



public class ClassPathXMLApplicationContext implements  ApplicationContext {
	
	//IOC����ʵ��
	
	 private File file;
	 private Map<String, Object> map = new HashMap<String, Object>();
	 private List<String> listAop = new ArrayList<String>();

	 public ClassPathXMLApplicationContext(String config_file) {
		 URL url = this.getClass().getClassLoader().getResource(config_file);

	     try {
	    	 file = new File(url.toURI());
	    	 XMLexcutionBeans();
	    	 XMLexcutionAop();
	    }catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
		}
	        
	 }

	 private void XMLexcutionBeans() throws Exception {     
		 SAXBuilder builder = new SAXBuilder();
		 
		 Document doc = builder.build(file);
	        
	        //�����ļ�ֻ��beans
	        //Element root = doc.getRootElement(); //��ȡ���ڵ�
	        
	       // List<Element> beans = root.getChildren();  //��ȡ���ڵ�������ӽڵ�
	       
		 Element root=doc.getRootElement();
	     XPathFactory xpathf=XPathFactory.instance();
	     XPathExpression<Object>objs=xpathf.compile("//bean");
	     List<Object>beans=objs.diagnose(root, false).getResult();
	        
	        
	       // XPath xpath = XPath.newInstance("//bean"); //�ҵ�����bean��� ,xpath���ʽ
	        
	        
           // List<Element> beans = (List<Element>) xpath.selectNodes(doc);  
        
	        Iterator<Object> i = beans.iterator();
	        
	        while (i.hasNext()) {
	            Element bean = (Element) i.next();
	            String id = bean.getAttributeValue("id");
	            String cls = bean.getAttributeValue("class");
	            
	            Object obj = Class.forName(cls).newInstance();//����bean
	            
	            Method[] method = obj.getClass().getMethods();//��ȡbean���з���
	            
	            List<Element> list = bean.getChildren("property");//��ȡ����property           
	            for (Element el : list) {
	                for (int n = 0; n < method.length; n++) {//����bean�����з���
	                    String name = method[n].getName();//��ȡbean�ķ�����
                    	String temp = name.substring(3, name.length()).toLowerCase();//��ȡ������set֮�󲿷�
                    	//Class<?>[] parameterTypes = method.getParameterTypes();
                    	
	                    if (name.startsWith("set")) {//�ҳ�����set����    
	                        if (el.getAttribute("name") != null)
	                            if (temp.equals(el.getAttribute("name").getValue())) {
	                                method[n].invoke(obj, el.getAttribute("value").getValue());//���ղ������ط���
	                            }
	                        if (el.getAttribute("ref") != null) {
	                            method[n].invoke(obj,map.get(el.getAttribute("ref").getValue()));//������bean
	                        }                   
	                    }
	                }
	            }
	            map.put(id, obj);
	        }
	    }
	 private void XMLexcutionAop() throws Exception {     
		 SAXBuilder builder = new SAXBuilder();
		 Document doc = builder.build(file);
	        
	        //�����ļ�ֻ��beans
	        //Element root = doc.getRootElement(); //��ȡ���ڵ�
	        
	       // List<Element> beans = root.getChildren();  //��ȡ���ڵ�������ӽڵ�
	       
		 Element root=doc.getRootElement();
	     XPathFactory xpathf=XPathFactory.instance();
	     XPathExpression<Object>objs=xpathf.compile("//Aop");
	     List<Object>beans=objs.diagnose(root, false).getResult();
	        
	        
	       // XPath xpath = XPath.newInstance("//bean"); //�ҵ�����bean��� ,xpath���ʽ
	        
	        
           // List<Element> beans = (List<Element>) xpath.selectNodes(doc);  
        
	        Iterator<Object> i = beans.iterator();
	        
	        while (i.hasNext()) {
	            Element bean = (Element) i.next();
	            String cls = bean.getAttributeValue("class");
	            listAop.add(cls);
	        }
	    }

	    public Object getBean(String name) {
	        return map.get(name);
	    }
	    public List<String> getAops() {
	        return listAop;
	    }
}

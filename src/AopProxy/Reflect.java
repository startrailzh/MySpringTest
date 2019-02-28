package AopProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Annotation.After;
import Annotation.Before;
import Annotation.MyAspect;
import Beans.IocFactory.ApplicationContext;
import Beans.IocFactory.ClassPathXMLApplicationContext;

public class Reflect {
	private Map<String,String> map ;   //������Ƿ������Լ���ע��
	private Map<String,String> AopData;
    public Reflect() throws ClassNotFoundException {
        map=new HashMap<>();
        getAnnotationClass();
    }

    public Map<String, String> getMap() {  // ���ﷵ�ص����Ѿ�ȫ����õ�map����ProxyUtilʹ��
        return map;
    }
    
    public Map<String,String> getAopData(){
    	return AopData;
    }
    
    public  void getAnnotationClass() throws ClassNotFoundException {
    	ApplicationContext context = new ClassPathXMLApplicationContext("application.xml");
    	List<String> listAop = context.getAops();
    	Iterator<String> i = listAop.iterator();
    	while(i.hasNext()){
            String[] str = new String[3];
        String clazzName=i.next();
        Class<?>  clazz =Class.forName(clazzName,false,Thread.currentThread().getContextClassLoader());   // ����Ϊ��ʡ��ֱ�Ӷ�̬�����˸���
        if (clazz.isAnnotationPresent(MyAspect.class)) {  //������ע����
            Method[] methods =clazz.getDeclaredMethods();   //��������
            for (Method method :methods) {
                if (method.isAnnotationPresent(Before.class)) {  // ��ȡע��
                   Before before =method.getAnnotation(Before.class); 
                    String beforeValue=before.value();  // ��ȡע���ֵ�Լ���ǰ������ַ�����÷���
                    map.put(method.getName()+ "-"+clazzName+"-"+"before",beforeValue.substring(0,beforeValue.length()-2));
                    // ������Ƿ�������ע�����Լ�ִ�е�˳������Ϊ��ʡ��ֱ�Ӿ��ں���д��
                }
                if (method.isAnnotationPresent(After.class)) {
                    After after =method.getAnnotation(After.class); 
                    String afterValue=after.value();
                    map.put(method.getName()+ "-"+clazzName+"-"+"after",afterValue.substring(0,afterValue.length()-2));
                }
            }
        }

    	}
    }
}

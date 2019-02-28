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
	private Map<String,String> map ;   //存入的是方法名以及其注解
	private Map<String,String> AopData;
    public Reflect() throws ClassNotFoundException {
        map=new HashMap<>();
        getAnnotationClass();
    }

    public Map<String, String> getMap() {  // 这里返回的是已经全部存好的map方面ProxyUtil使用
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
        Class<?>  clazz =Class.forName(clazzName,false,Thread.currentThread().getContextClassLoader());   // 这里为了省事直接动态加载了该类
        if (clazz.isAnnotationPresent(MyAspect.class)) {  //假设是注解类
            Method[] methods =clazz.getDeclaredMethods();   //遍历方法
            for (Method method :methods) {
                if (method.isAnnotationPresent(Before.class)) {  // 获取注解
                   Before before =method.getAnnotation(Before.class); 
                    String beforeValue=before.value();  // 获取注解的值以及当前类的名字方面调用方法
                    map.put(method.getName()+ "-"+clazzName+"-"+"before",beforeValue.substring(0,beforeValue.length()-2));
                    // 存入的是方法名和注解名以及执行的顺序，这里为了省事直接就在后面写了
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

package AopProxy;

import java.lang.reflect.Method;

import java.util.Map;



public class ProxyUtil {

    Reflect reflect;

    public ProxyUtil() throws ClassNotFoundException {
    	
    }



    //�÷����������
    public void before(ProxyEntity proxyEntity) throws Throwable {
    	
        reflect = new Reflect();
    	
    	//Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(), proxyEntity.getArgs());
    	
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(
        		proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, 
        		proxyEntity.getMethod().toString().indexOf("("));//��ȡ������
        Map<String, String> methodMap = reflect.getMap();
        for (Map.Entry<String, String> map : methodMap.entrySet()) {
            if (map.getValue().equals(proxyMethodValue)) {
                String[] str = mapKeyDivision(map.getKey());
                if (str[2].equals("before")) {
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // ���ظ���
                    Method method = clazz.getDeclaredMethod(str[0]);
                    method.invoke(clazz.newInstance(), null); // ������÷���
                }
            }
        }
        //return object;
    }
    public void after(ProxyEntity proxyEntity) throws Throwable {
    	
    	//Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(), proxyEntity.getArgs());
    	
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(
        		proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, 
        		proxyEntity.getMethod().toString().indexOf("("));
        Map<String, String> methodMap = reflect.getMap();
        for (Map.Entry<String, String> map : methodMap.entrySet()) {
            if (map.getValue().equals(proxyMethodValue)) {
                String[] str = mapKeyDivision(map.getKey());
                if (str[2].equals("after")) {
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // ���ظ���
                    Method method = clazz.getDeclaredMethod(str[0]);
                    method.invoke(clazz.newInstance(), null); // ������÷���
                }
            }
        }
       // return object;
    }
 
//�ֽ�map����ļ�����Ϊ��������˷����������Լ�ִ��˳��
    private String[] mapKeyDivision(String value) {
        String[] str = new String[3];
        str[0] = value.substring(0, value.indexOf("-"));  //ע������ķ���
        str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-")); //ע�����ڵ���
        str[2]=value.substring(value.lastIndexOf("-")+1,value.length()); //��before����after
        return str;
    }
    
}
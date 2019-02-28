package AopProxy;

import java.lang.reflect.Method;

import Annotation.MyAspect;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxy implements MethodInterceptor {
    private Object target;
    private ProxyUtil proxyUtil ;
    //private String url="Music.Player";//ʹ��ע������url
    
    public CGLIBProxy(Object target) throws ClassNotFoundException {
    	this.target = target;
        proxyUtil =new ProxyUtil();
    }
    @Override
    //������
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        
        //Class<?>  clazz =Class.forName(url,false,Thread.currentThread().getContextClassLoader());
        //if (clazz.isAnnotationPresent(MyAspect.class)){
        	
        ProxyEntity proxyEntity =new ProxyEntity(proxy,this.target.getClass(),obj,method,args);
        
		 //System.out.println("Before");//before
		 proxyUtil.before(proxyEntity);
		 
		 Object object=proxy.invokeSuper(obj, args);//main method
		 
		 //System.out.println("After");//after
		 proxyUtil.after(proxyEntity);
		 
		 return object;
    	//}
    	//else{
    	//	Object object=proxy.invokeSuper(obj, args);//main method
    	//	return object;
    	//}
    }
}

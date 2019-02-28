package AopProxy;

import net.sf.cglib.proxy.Enhancer;

public class medium {
	
	public static Object test(Object obj){

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		try {
			enhancer.setCallback(new CGLIBProxy(obj));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enhancer.create();
	}
}

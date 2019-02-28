package test.main;

import Beans.IocFactory.ApplicationContext;
import Beans.IocFactory.ClassPathXMLApplicationContext;
import test.base.StudentService;

public class IocTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ApplicationContext context = new ClassPathXMLApplicationContext("application.xml");
        StudentService stuServ = (StudentService) context.getBean("StudentService");
        stuServ.testIocTest();

        // Method[] method=stuServ.getClass().getMethods();
        //System.out.println(method[0].getParameterTypes());
        //Object obj=method[0].getParameterTypes();
        // Class.forName(obj.getClass());
    }
}

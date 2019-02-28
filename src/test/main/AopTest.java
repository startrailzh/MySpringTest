package test.main;

import AopProxy.medium;
import test.base.StudentService;

public class AopTest {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub

	    StudentService student = new StudentService();   
	    ((StudentService)medium.test(student)).testAopTest();
	    //((StudentService)medium.test(student)).testIocTest();
	}
}

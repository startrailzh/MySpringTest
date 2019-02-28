package test.base;

import Annotation.After;
import Annotation.Before;
import Annotation.MyAspect;

@MyAspect // ��������һ���е���
public class AopTestBase {
    @Before("test.base.StudentService.testAopTest()")  // ǰ��֪ͨ��������sing���������õ�ʱ��÷����ᱻ����֮ǰ����
    public void doBefore() {
        System.out.println("doBefore");
    }

    @After("test.base.StudentService.testAopTest()") // ͬ���ڵ���sing����֮���������ø÷���
    public void doAfter() {
        System.out.println("doAfter");
    }
}
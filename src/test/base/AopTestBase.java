package test.base;

import Annotation.After;
import Annotation.Before;
import Annotation.MyAspect;

@MyAspect // 表明这是一个切点类
public class AopTestBase {
    @Before("test.base.StudentService.testAopTest()")  // 前置通知，当调用sing方法被调用的时候该方法会被在它之前调用
    public void doBefore() {
        System.out.println("doBefore");
    }

    @After("test.base.StudentService.testAopTest()") // 同理，在调用sing方法之后再来调用该方法
    public void doAfter() {
        System.out.println("doAfter");
    }
}
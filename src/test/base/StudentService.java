package test.base;  

  
public class StudentService {  
	private  Student student;  
	
	
	public Student getStudent() {  
		return student;  
	}  
	public void setStudent(Student student) {  
		this.student = student;  
	}
	
	
    public void testIocTest(){
    	student.selfIntroDuction();
    }
    public void testAopTest(){
    	System.out.println("Aoptest");
    }
}
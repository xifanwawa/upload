
public class Test {
	public Test(){
		System.out.println("构造方法");
	}
	
	public static void method1(){
		System.out.println("静态方法");
		Test test = new Test();		
		test.method2();
	}
	
	public void method2(){
		System.out.println("成员方法");
	}
}

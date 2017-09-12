/**
 * 添加一个注释
 * @author Administrator
 *
 */
public class Test {
	public Test(){
		System.out.println("���췽��");
	}
	
	public static void method1(){
		System.out.println("��̬����");
		Test test = new Test();		
		test.method2();
	}
	
	public void method2(){
		System.out.println("��Ա����");
	}
}

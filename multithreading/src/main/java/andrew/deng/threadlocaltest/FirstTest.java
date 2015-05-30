package andrew.deng.threadlocaltest;

public class FirstTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InheritableThreadLocal one=new InheritableThreadLocal();
		one.set(1);
//		System.out.println(one.get());
		one.set(2);
//		System.out.println(one.get());
		
		InheritableThreadLocal two=new InheritableThreadLocal();
		two.set("g");
		System.out.println(two.get());
		two.set("s");
		System.out.println(two.get());


	}
	
	

}

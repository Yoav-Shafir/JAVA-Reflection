package methods;

import java.io.IOException;
import java.lang.reflect.Method;

class A {
	private double data;
	private void add(double v) {}
	protected void sub(int s) {}
	public double getData() throws IOException { return this.data; }
}

class B extends A {
	private String name;
	private void mult(int len) {}
	protected void div(double v) {}
	public String getName() { return this.name; }
}

public class Exe1 {
	public static void main(String[] args) {
		try {
			showPublicMethod(B.class);
			
			System.out.println("--------------------");
			
			showAnyMethod(B.class);
			
			System.out.println("--------------------");
			
			showPublicMethods(B.class);
			
			System.out.println("--------------------");
			
			showAllMethods(A.class);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private static void showPublicMethod(Class<?> c) throws NoSuchMethodException {
		System.out.println("Public method for " + c.getName());
		Method method1 = c.getMethod("getName"); // inherited methods as well.
		System.out.println(method1);
		Method method2 = c.getMethod("getData");
		System.out.println(method2);
	}
	
	private static void showAnyMethod(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nAny method for " + c.getName());
		Method method = c.getDeclaredMethod("div",double.class);
		System.out.println(method);
	}
	
	private static void showPublicMethods(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nPublic methods for " + c.getName());
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}
	
	private static void showAllMethods(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nAll methods for " + c.getName());
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}
		
}

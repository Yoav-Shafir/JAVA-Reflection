package constructors;

import java.io.IOException;
import java.lang.reflect.Constructor;

class B {
	public B() {}
	public B(boolean flag) {}
	public B(String s, int i) {}
	public B(int i) throws IOException {}
	protected B(float f) {}
	private B(char c) {}
}

public class Exe1 {
	public static void main(String[] args) {
		try {
			showPublicConstructor(B.class);
			
			System.out.println("--------------------");
			
			showAnyConstructor(B.class);
			
			System.out.println("--------------------");
			
			showPublicConstructors(B.class);
			
			System.out.println("--------------------");
			
			showAllConstructors(B.class);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private static void showPublicConstructor(Class<?> c) throws NoSuchMethodException {
		System.out.println("Public Constructor for " + c.getName());
		Constructor<?> ctor1 = c.getConstructor(); // default constructor;
		System.out.println(ctor1);
		Constructor<?> ctor2 = c.getConstructor(String.class, int.class); // if not found, will throw "NoSuchMethodException"
		System.out.println(ctor2);
	}
	
	private static void showAnyConstructor(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nAny Constructor for " + c.getName());
		Constructor<?> ctor = c.getDeclaredConstructor(float.class); // will find any visibility.
		System.out.println(ctor);
	}
	
	private static void showPublicConstructors(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nAll Constructor for " + c.getName());
		Constructor<?>[] constrs = c.getConstructors(); // returns all public constructors.
		for (Constructor<?> constructor : constrs) {
			System.out.println(constructor);
		}
	}
	
	private static void showAllConstructors(Class<?> c) throws NoSuchMethodException {
		System.out.println("\nAll Constructor for " + c.getName());
		Constructor<?>[] constrs = c.getDeclaredConstructors();
		for (Constructor<?> constructor : constrs) {
			System.out.println(constructor);
		}
	}
}

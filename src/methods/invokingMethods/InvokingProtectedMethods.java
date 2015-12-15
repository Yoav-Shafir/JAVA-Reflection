package methods.invokingMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

class D {
	protected void write(int i, String s) {
		System.out.format("D::write(int, String): %d, %s\n", i, s);
	}
	public void print(String s, int i) {
		System.out.format("D::write(String, int): %s, %d\n", s, i);
	}
	protected void print(int i, String s) {
		System.out.format("D::print(int, String): %d, %s\n", i, s);
	}
	public String read(int i, String s) {
		System.out.format("D::read(int, String): %d, %s\n", i, s);
		return s;
	}
}

class E extends D {
	protected void compute(int i, String s) {
		System.out.format("E::compute(int, String): %d %s\n", i, s);
	}
}

public class InvokingProtectedMethods {
	public static void main(String[] args) {
		Object[] params = {10, "data"};
		Class<?>[] ptypes = {int.class, String.class};
		try {
			E obj = new E();
			invokeProtectedVoidMethods(obj, D.class, params, ptypes);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static <T> void invokeProtectedVoidMethods(T obj, Class<?> superClass,
		Object[] params, Class<?>... ptypes) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method[] methods = getProtectedMethods(obj.getClass(), superClass);
		for (Method method : methods) {
			if (method.getReturnType().equals(void.class)
					&& Arrays.equals(method.getParameterTypes(), ptypes)) {
				method.setAccessible(true);
				method.invoke(obj, params);
				System.out.println(method); // print all protected methods.
			}
		}
		
	}

	private static Method[] getProtectedMethods(Class<?> thisClass, Class<?> superClass) {
		ArrayList<Method> list = new ArrayList<>();
		
		for (;; thisClass = thisClass.getSuperclass()) {
			Method[] methods = thisClass.getDeclaredMethods();
			for (Method method : methods) {
				if (Modifier.isProtected(method.getModifiers())) {
					list.add(method);
				}
			}
			if (thisClass == superClass) {
				break;
			}
		}
		return list.toArray(new Method[list.size()]);
	}
}

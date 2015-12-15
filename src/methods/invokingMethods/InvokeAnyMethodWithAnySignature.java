package methods.invokingMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

class Any {
	private Integer read(int n, String s) {
		System.out.format("read(int, String): %d %s\n", n ,s);
		return n;
	}
	private String read(int m) {
		System.out.format("read(int): %d\n", m);
		return "data";
	}
	public void setup() {
		System.out.println("setup()\n");
	}
}

public class InvokeAnyMethodWithAnySignature {
	public static void main(String[] args) {
		Object[] oneParam = {10};
		Class<?>[] oneType = {int.class};
		Object[] twoParams = {10, "data"};
		Class<?>[] twoTypes = {int.class, String.class};
		
		try {
			Any obj = new Any();
			invokeAnyMethod(obj, void.class, null);
			String s = invokeAnyMethod(obj, String.class, oneParam, oneType);
			System.out.format("returns %s\n", s);
			Integer m = invokeAnyMethod(obj, Integer.class, twoParams, twoTypes);
			System.out.format("returns %d\n", m);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static <T, R> R invokeAnyMethod(T obj, Class<?> retType, Object[] params, Class<?>... ptypes) 
		throws InvocationTargetException, IllegalAccessException {
		
			Class<?> c = obj.getClass();
			Method[] methods = c.getDeclaredMethods();
			Method theMethod = null;
			for (Method method : methods) {
				if (Arrays.equals(method.getParameterTypes(), ptypes)) {
					theMethod = method;
					break;
				}
			}
			if (theMethod == null || !theMethod.getReturnType().equals(retType)) {
				throw new IllegalArgumentException();
			}
			if (!Modifier.isPublic(theMethod.getModifiers())) {
				theMethod.setAccessible(true);
			}
			return (R)theMethod.invoke(obj, params);
	}
}

package methods;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Exe2 {
	public static void main(String[] args) {
		showPublicMethods(Object.class);
	}

	private static void showPublicMethods(Class<?> c) {
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			System.out.format("%s ", Modifier.toString(method.getModifiers()));
			String retType = method.getReturnType().getName();
			System.out.format("%s %s( ", retType, method.getName());
			Class<?>[] ptypes = method.getParameterTypes();
			for (Class<?> param : ptypes) {
				System.out.format("%s ", param.getName());
			}
			System.out.format(")");
			Class<?>[] extypes = method.getExceptionTypes();
			if (extypes.length > 0) {
				System.out.format(" throws ");
				for (Class<?> ex : extypes) {System.out.format(" throws ");
					System.out.format("%s ", ex.getName());
				}
			}
			System.out.println();
		}
	}
}

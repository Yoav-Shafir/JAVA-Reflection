package annotations.methodAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import annotations.Platform;
import annotations.Token;

public class MethodAnnotations {
	public static void main(String[] args) {
		showMethodAnnotations(MyThirdClass.class);
	}

	private static void showMethodAnnotations(Class<?> c) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.format("Method %s Annotation: %s\n", method.getName(), annotation);
				if (annotation instanceof Token) {
					Token token = (Token) annotation;
					System.out.format("%s %s\n", token.name(), token.code());
				}
			}
			if (method.isAnnotationPresent(Platform.class)) {
				Platform platform = method.getAnnotation(Platform.class);
				System.out.format("%s\n", platform.what());
			}
		}
	}
}

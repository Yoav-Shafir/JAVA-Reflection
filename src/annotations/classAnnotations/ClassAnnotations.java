package annotations.classAnnotations;

import java.lang.annotation.Annotation;

import annotations.Platform;
import annotations.Token;

public class ClassAnnotations {
	public static void main(String[] args) {
		showClassAnnotations(MyClass.class);
	}
	
	private static void showClassAnnotations(Class<?> c) {
		Annotation[] annotations = c.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.format("Class %s Annotations: %s\n", c.getName(), annotation);
			if (annotation instanceof Token) {
				Token token = (Token) annotation;
				System.out.format("%s %s\n", token.name(), token.code());
			}
		}
		if (c.isAnnotationPresent(Platform.class)) {
			Platform platform = c.getAnnotation(Platform.class);
			System.out.format("%s\n", platform.what());
		}
		if (Token.class.isAnnotation()) {
			System.out.format("Token is an annotation class\n");
		}
	}
}

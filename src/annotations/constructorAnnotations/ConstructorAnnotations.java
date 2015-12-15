package annotations.constructorAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import annotations.Platform;
import annotations.Token;

public class ConstructorAnnotations {
	public static void main(String[] args) {
		showConstructorAnnotations(MyLastClass.class);
	}

	private static void showConstructorAnnotations(Class<?> c) {
		Constructor<?>[] constrs = c.getDeclaredConstructors();
		for (Constructor<?> ctor : constrs) {
			Annotation[] annotations = ctor.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.format("Constructor %s Annotation: %s\n", ctor.getName(), annotation);
				if (annotation instanceof Token) {
					Token token = (Token) annotation;
					System.out.format("%s %s\n", token.name(), token.code());
				}
			}
			if (ctor.isAnnotationPresent(Platform.class)) {
				Platform platform = ctor.getAnnotation(Platform.class);
				System.out.format("%s\n", platform.what());
			}
			
		}
	}
}

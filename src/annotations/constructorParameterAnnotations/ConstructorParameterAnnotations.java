package annotations.constructorParameterAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import annotations.Platform;
import annotations.Token;

public class ConstructorParameterAnnotations {
	public static void main(String[] args) {
		showConstructorParameterAnnotations(TheFinalClass.class);
	}

	private static void showConstructorParameterAnnotations(Class<?> c) {
		Constructor<?>[] constrs = c.getDeclaredConstructors();
		for (Constructor<?> ctor : constrs) {
			Annotation[][] annotations = ctor.getParameterAnnotations();
			for (Annotation[] annotationArray : annotations) {
				for (Annotation annotation : annotationArray) {
					System.out.format("%s Constructor Parameter Annotation: %s\n", ctor.getName(), annotation);
					if (annotation instanceof Token) {
						Token token = (Token) annotation;
						System.out.format("%s %s\n", token.name(), token.code());
					}
					if (annotation instanceof Platform) {
						Platform platform = (Platform) annotation;
						System.out.format("%s\n", platform.what());
					}
				}
			}
		}
	}
}

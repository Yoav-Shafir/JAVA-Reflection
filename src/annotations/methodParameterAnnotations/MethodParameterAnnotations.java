package annotations.methodParameterAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import annotations.Platform;
import annotations.Token;

public class MethodParameterAnnotations {
	public static void main(String[] args) {
		showMethodParameterAnnotations(YetAnotherClass.class);
	}

	private static void showMethodParameterAnnotations(Class<?> c) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			Annotation[][] annotations = method.getParameterAnnotations();
			
			// 'annotations' represents array of method parameters. 
			for (Annotation[] annotationArray : annotations) {
				
				// 'annotationArray' represents annotations array for each method.
				for (Annotation annotation : annotationArray) {
					System.out.format("%s Method Parameter Annotation: %s\n", method.getName(), annotation);
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

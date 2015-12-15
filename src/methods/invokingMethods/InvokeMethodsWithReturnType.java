package methods.invokingMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class B {
	public String read() {
		return "Reading...";
	}
}

public class InvokeMethodsWithReturnType {
	public static void main(String[] args) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		B obj = new B();
		Class<?> c = obj.getClass();
		
		// invoke public read() method.
		Method method = c.getMethod("read");
		String string = (String)method.invoke(obj);
		System.out.println(string);
	}
}

package methods.invokingMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class C {
	public void encode(String str) {
		System.out.println("Encoding " + str);
	}
}

public class PrivateMethodAccess {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		C obj = new C();
		Class<?> c = obj.getClass();
		
		// invoke private encode(String) method.
		Method method = c.getDeclaredMethod("encode", String.class);
		
		// handle java security manager.
		// this is a private method, we need to set permission.
		method.setAccessible(true);
		method.invoke(obj, "secret");
		
	}
}

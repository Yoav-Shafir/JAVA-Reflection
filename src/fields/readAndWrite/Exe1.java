package fields.readAndWrite;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class A {
	private int num;
	public A(int num) {
		this.num = num;
	}
	public String brothers = "Groucho Chico Harpo";
	public String group = "BILL BRUCE JIM";
	public final String guy = "Bob";
}

public class Exe1 {
	public static void main(String[] args) {
		try {
			A object = new A(3);
			modifyPublicFields(object);
			System.out.println(object.brothers);
			System.out.println(object.group);
			System.out.println(object.guy);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}	
	}
	
	// modify String fields which are public and not final. 
	private static <T> void modifyPublicFields(T obj) throws IllegalAccessException, IllegalArgumentException {
		Class<?> c = obj.getClass();
		Field[] fields = c.getFields();	
		for (Field field : fields) {
			if (!Modifier.isFinal(field.getModifiers()) && field.getType() == String.class) {
				String str = (String)field.get(obj);
				field.set(obj, str.toLowerCase());
			}
		}
	}
}

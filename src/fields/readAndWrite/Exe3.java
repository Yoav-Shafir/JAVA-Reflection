package fields.readAndWrite;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class B {
	private int num;
	public B(int num) {
		this.num = num;
	}
	public int count = 10;
	public int val = 20;
	public final int data = 30;
}

// primitive types.
public class Exe3 {
	public static void main(String[] args) {
		try {
			B object = new B(3);
			modifyPublicFields(object);
			System.out.println(object.count);
			System.out.println(object.val);
			System.out.println(object.data);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private static <T> void modifyPublicFields(T obj) throws IllegalArgumentException, IllegalAccessException {
		Class<?> c = obj.getClass();
		Field[] fields = c.getFields(); // public fields.
		for (Field field : fields) {
			if (!Modifier.isFinal(field.getModifiers()) && field.getType() == int.class) {
				int val = field.getInt(obj);
				field.setInt(obj, ++val);
			}
		}
		
	}
}

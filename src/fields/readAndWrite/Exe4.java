package fields.readAndWrite;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


class C {
	private int num;
	public C(int num) {
		this.num = num;
	}
	private String brothers = "Groucho Chico Harpo";
	private String group = "BILL BRUCE JIM";
	private final String guy = "Bob";
	public String getBrothers() {
		return this.brothers;
	}
	public String getGroup() {
		return this.group;
	}
	public String getGuy() {
		return this.guy;
	}
}

// access private fields.
public class Exe4 {
	public static void main(String[] args) {
		try {
			C object = new C(3);
			modifyPrivateFields(object);
			System.out.println(object.getBrothers());
			System.out.println(object.getGroup());
			System.out.println(object.getGuy());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private static <T> void modifyPrivateFields(T obj) 
			throws IllegalArgumentException, IllegalAccessException, SecurityException {
		
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			int mods = field.getModifiers();
			if (Modifier.isPrivate(mods) && !Modifier.isFinal(mods) && field.getType() == String.class) {
				
				// if we comment this line we'll get an exception:
				// java.lang.IllegalAccessException ... an not access a member of class fields.readAndWrite.C with modifiers "private".
				field.setAccessible(true);
				
				String str = (String)field.get(obj);
				field.set(obj, str.toLowerCase());
			}
		}
	}
}

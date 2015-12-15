package primitives;

public class LoadingPrimitives {
	public static void main(String[] args) {
		
		// good for reference types
		try {
			String name = "java.lang.String";
			Class<?> c = getClass(name);
			System.out.println(c.getName());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		// good for primitive types.
		try {
			String name = "int";
			Class<?> c = getPrimitiveClass(name);
			System.out.println(c.getName());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static Class<?> getClass(String name) throws ClassNotFoundException {
		return Class.forName(name);
	}
	
	public static Class<?> getPrimitiveClass(String name) throws ClassNotFoundException {
		if (name.equals("char")) return char.class;
		if (name.equals("int")) return int.class;
		if (name.equals("float")) return float.class;
		if (name.equals("double")) return double.class;
		return null;
	}
}

package arrays;

import java.lang.reflect.Array;

// creating one dimensional array at run time.
public class ArrayCreate {
	public static void main(String[] args) {
		String types[] = {"java.lang.Integer", "java.lang.Float", "java.lang.Double"};
		int len = 5;
		try {
			for (String type : types) {
				Number[] array	= createArray(type, len += 5);
				System.out.format("%s ", array.getClass().getSimpleName());
				System.out.format("length = %d\n ", Array.getLength(array));
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] createArray(String className, int len) 
			throws ClassNotFoundException, NegativeArraySizeException {
		Class<?> type = Class.forName(className);
		return (T[])Array.newInstance(type, len);
	}
}

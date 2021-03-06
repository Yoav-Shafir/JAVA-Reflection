package arrays;

import java.lang.reflect.Array;

public class ArrayGetAndSet {
	public static void main(String[] args) {
		try {
			Object array1 = makeArray(10, int.class, 1);
			printArray(array1);
			
			Object array2 = makeArray(10, double.class, 1.23);
			printArray(array2);
			
			Object array3 = makeArray(10, String.class, "data");
			printArray(array3);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static Object makeArray(int len, Class<?> c, Object ival) {
		Object array = Array.newInstance(c, len);
		for (int i = 0; i < len; i++) {
			Array.set(array, i, ival);
		}
		return array;
	}
	
	private static void printArray(Object array) {
		Class<?> c = array.getClass().getComponentType();
		for (int i = 0; i < Array.getLength(array); i++) {
			if (c == int.class) {
				int elem = Array.getInt(array, i);
				System.out.format("%d ", elem);
			}
			if (c == double.class) {
				double elem = Array.getDouble(array, i);
				System.out.format("%4.2f ", elem);
			}
			if (c == String.class) {
				String elem = (String)Array.get(array, i);
				System.out.format("%s ", elem);
			}
		}
		System.out.println();
	}
}

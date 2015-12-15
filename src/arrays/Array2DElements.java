package arrays;

import java.lang.reflect.Array;

public class Array2DElements {
	public static void main(String[] args) {
		Object array1 = make2DArray(2, 3, int.class, 1);
		print2DArray(array1);
		
		Object array2 = make2DArray(2, 3, String.class, "data");
		print2DArray(array2);
	}

	private static Object make2DArray(int rows, int cols, Class<?> c, Object ival) {
		Object array = Array.newInstance(c, rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Array.set(Array.get(array, i), j, ival);
			}
		}
		return array;
	}
	
	private static void print2DArray(Object array) {
		int rows = Array.getLength(array);
		int cols = Array.getLength(Array.get(array, 0));
		Class<?> c = array.getClass().getComponentType();
		for (int i = 0; i < rows; i++) {
			Object row = Array.get(array, i);
			for (int j = 0; j < cols; j++) {
				if (c == int[].class) {
					int elem = Array.getInt(row, j);
					System.out.format("%d ", elem);
				}
				if (c == double[].class) {
					double elem = Array.getDouble(row, j);
					System.out.format("%d ", elem);
				}
				if (c == String[].class) {
					String elem = (String)Array.get(row, j);
					System.out.format("%s ", elem);
				}
			}
		}
		System.out.println();
	}
	
}

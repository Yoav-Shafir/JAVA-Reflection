package arrays;

import java.lang.reflect.Array;

public class Array2DCreate {
	public static void main(String[] args) {
		String[] names = {"java.lang.Integer", "java.lang.Float", "java.lang.Double"};
		int rows = 2, cols = 3;
		try {
			for (String name : names) {
				Number[][] array = create2DArray(name, rows, cols);
				System.out.format("%s ", array.getClass().getSimpleName());
				System.out.format("rows = %d cols = %d\n", 
						Array.getLength(array), Array.getLength(Array.get(array, 0)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T[][] create2DArray(String className, int rows, int cols)
		throws ClassNotFoundException, NegativeArraySizeException {
			Class<?> type = Class.forName(className);
			return (T[][])Array.newInstance(type, rows, cols);
		
	}
}

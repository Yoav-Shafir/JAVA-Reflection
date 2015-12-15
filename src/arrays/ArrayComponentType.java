package arrays;

import javax.swing.JButton;

public class ArrayComponentType {
	public static void main(String[] args) {
		double[] vals = new double[10];
		JButton[] buttons = new JButton[4];
		String[][] grid = new String[4][3];
		showArrayComponents(vals);
		showArrayComponents(buttons);
		showArrayComponents(grid);
	}

	private static <T >void showArrayComponents(T obj) {
		Class<?> c = obj.getClass();
		if (c.isArray())
			System.out.format("%s: %s\n", c, c.getComponentType().getName());
		
	}
}

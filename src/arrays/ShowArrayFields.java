package arrays;

import java.lang.reflect.Field;

import javax.swing.JButton;

class G {
	public boolean visible;
	public int[] codes;
	private boolean[] states;
	public float[][] martix;
	public JButton[] buttons;
}

public class ShowArrayFields {
	public static void main(String[] args) {
		showArrayFields(G.class);
	}

	private static void showArrayFields(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isArray()) {
				System.out.format("%s: %s\n", field, field.getType().getName());
			}
		}
	}
}

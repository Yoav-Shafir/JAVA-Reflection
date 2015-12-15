package fields.readAndWrite;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Exe2 {
	public static void main(String[] args) {
		
		// just a warm up.
		Color c = new Color(0);
		Color b = c.BLACK;
		System.out.println(b.toString());
		
		try {
			Color[] colors = getColotFields();
			System.out.println("colors size: " + colors.length);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	private static Color[] getColotFields() throws IllegalAccessException {
		ArrayList<Color> list = new ArrayList<Color>();
		
		// Color object with no color.
		Color colorObject = new Color(0);
		
		// Color class has static fields of type Color as well.
		// for example:
		// Color black = colorObject.BLACK; 
		
		Field[] colorFields = Color.class.getFields(); // srote all public fields.
		for (Field field : colorFields) {
			String className = field.getDeclaringClass().getSimpleName();
			String fieldName = field.getName();
			if (Modifier.isStatic(field.getModifiers()) && // check if static.
				fieldName.equals(fieldName.toUpperCase()) && // check if uppercase.
				field.getType() == Color.class) { // check type is Color
					
					// returns the value of the field represented by this Field, on the specified object.
					Color color = (Color)field.get(colorObject);
					list.add(color);
					System.out.format("%s.%-15s%s\n", className, fieldName, color);
				}
		}
		return list.toArray(new Color[list.size()]);
	}
}

package fields;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

class A {
	public String name = "John";
}

class B extends A {
	private char c = 'x';
	public 	int num = 12;
	public final double val = 34.5;
	public static String s = "abc";
}

public class Exe2 {
	public static void main(String[] args) {
		for (Field f: getPublicNonStaticFields(B.class))
			System.out.println(f);    // calls f.toString()
		
		System.out.println("--------------------");
		
		Point p = new Point();
		for (Field f: getPublicNonStaticFields(p.getClass())) {
			System.out.println(Modifier.toString(f.getModifiers()) +
	                " " + f.getType().getName() + " " +
	                    f.getDeclaringClass().getName() +
	                        "." + f.getName());
		}
			
	}
	
	static ArrayList<Field> getPublicNonStaticFields(Class<?> c) {
		ArrayList<Field> list = new ArrayList<Field>();
		Field[] fields = c.getFields(); // returns only public fields - including parent classes;
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				list.add(field);
			}
		}
		return list;
	}
}

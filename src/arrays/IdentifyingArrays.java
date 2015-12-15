package arrays;

import java.awt.Color;

public class IdentifyingArrays {
	public static void main(String[] args) {
		
		boolean arrayObject;
		Class<?> c1 = Color.class; // not array
		arrayObject = c1.isArray(); // false
	}
}

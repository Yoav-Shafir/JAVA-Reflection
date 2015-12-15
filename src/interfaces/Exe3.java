package interfaces;

import java.lang.reflect.Modifier;

// modifiers
public class Exe3 {
	public static void main(String[] args) {
		checkInterface(Cloneable.class);
	}
	
	static void checkInterface(Class<?> c) {
		
		// returns the Java language modifiers for this class 
		// or interface, encoded in an integer
		int modifierInt = c.getModifiers();
		System.out.println(c.getName() + " modifier " + modifierInt);	
		
		// return a string describing the access modifier 
		// flags in the specified modifier.
		String modifierStr = Modifier.toString(modifierInt); 
		
		System.out.println(c.getName() + " is " + modifierStr);		
	}
}

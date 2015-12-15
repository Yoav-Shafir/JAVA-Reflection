	package interfaces;

import javax.naming.directory.Attributes;


// example of ho to determine if the specified Class object represents an interface type
// using the isInterface() method.
public class Exe2 {
	public static void main(String[] args) {
		
		Class<?>[] names = new Class<?>[] {String.class, Attributes.class};
		showInterfacesClasses(names);
	}
	
	public static void showInterfacesClasses(Class<?>[] names) {
		for (Class<?> c : names) {
			if (c.isInterface())
				System.out.println(c.getName() + " is an interface");
			else
				System.out.println(c.getName() + " is a class");
		}
	}
}

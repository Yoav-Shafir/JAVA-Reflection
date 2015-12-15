package interfaces;

import javax.naming.directory.Attributes;

// get all interfaces of a Class/Interface.
public class Exe1 {
	public static void main(String[] args) {
		
		// example1 - passing a Class.
		showInterfaceNames(String.class);
		
		// example2 - passing an Interface.
		showInterfaceNames(Attributes.class);
	}
	
	static void showInterfaceNames(Class<?> cls) {
		Class[] interfaces = cls.getInterfaces();
		for (Class<?> intf : interfaces) {
			System.out.println(intf.getName());
		}
		
	}
				
}

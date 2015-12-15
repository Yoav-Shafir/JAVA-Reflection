package objects;

import javax.swing.JComponent;

// instantiate no args constructor.
public class Exe1 {
	public static void main(String[] args) {
		String[] names = {"javax.swing.JButton", "javax.swing.JTextField"};
		
		try {
			for (String name : names) {
				JComponent comp = createObject(name);
				comp.setVisible(false);
				System.out.println(comp.getClass().getName());
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	// option 1. create an object of any type using Generic.
	@SuppressWarnings("unchecked")
	private static<T> T createObject(String className) 
		throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		Class<?> c = Class.forName(className);
		return (T) c.newInstance(); // call the default no args constructor.
	}
	
//  option 2. create an object of JComponent type.
//	private static JComponent createObject(String className) 
//		throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		
//		Class<?> c = Class.forName(className);
//		return (JComponent) c.newInstance(); // call the default no args constructor.
//	}
	
	
}

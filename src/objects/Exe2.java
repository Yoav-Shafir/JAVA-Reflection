package objects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;

// calling constructor with args.
public class Exe2 {
	public static void main(String[] args) {
		Object[] params = {"Input", 20};
		Class<?>[] ptypes = {String.class, int.class};
		String[] names = {"javax.swing.JTextField", "javax.swing.JPasswordField"};
		
		try {
			for (String name : names) {
				JComponent comp = createObject(name, params, ptypes);
				comp.setVisible(false);
				System.out.println(comp.getClass().getName());
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T createObject(String className, Object[] params,
		Class<?>[] ptypes) throws ClassNotFoundException, InstantiationException, 
			IllegalAccessException, NoSuchMethodException, IllegalArgumentException, 
				InvocationTargetException {
		
		// get the class.
		Class<?> c = Class.forName(className);
		// get a specific constructor by a specific signature.
		Constructor<?> constr = c.getConstructor(ptypes); 
		// create new instance passing the corresponding params.
		return (T)constr.newInstance(params);
	}
}

package xmlSerialization;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public class Helper {
	
	/**
     * Returns an array of the instance variablies of the 
     * the specified class.  An instance variable is defined
     * to be a non-static field that is declared by the class
     * or inherited.
     *
     * @return java.lang.Field[]
     * @param cls java.lang.Class
     */
	public static Field[] getInstanceVariables(Class cls) {
		List accum = new LinkedList();
		while (cls != null) {
		    Field[] fields = cls.getDeclaredFields();
		    for (int i=0; i<fields.length; i++) {
				if (!Modifier.isStatic(fields[i].getModifiers())) {
				    accum.add(fields[i]);
				}
		    }
		    cls = cls.getSuperclass();
		}
		Field[] retvalue = new Field[accum.size()];
		return (Field[]) accum.toArray(retvalue);
    }
}

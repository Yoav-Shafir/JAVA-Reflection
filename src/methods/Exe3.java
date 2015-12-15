package methods;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

// Getters & Setters.

class Person {
    private String name;
    private int age;
    private boolean deceased;
    public Person() { }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        deceased = false;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isDeceased() { return deceased; }
    public void setDeceased(boolean deceased) { this.deceased = deceased; }
    public boolean isTeenager() {
        return (age > 12 && age < 20) ? true : false;
    }
    @Override public String toString() { return name + " " + age; }
}

public class Exe3 {
	public static void main(String[] args) {
		for (Method method : findGettersSetters(Person.class)) {
			System.out.println(method);
		}
	}
	
	static ArrayList<Method> findGettersSetters(Class<?> c) {
        ArrayList<Method> list = new ArrayList<Method>();
        
        // get all methods declared on the class (not inherited methods)
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method) || isSetter(method)) {
                list.add(method);
            }    
        }
        return list;
    }
	
	public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
            !method.getReturnType().equals(void.class) &&
            method.getParameterTypes().length == 0 &&
            method.getName().matches("^get[A-Z].*")) {
        	return true;
        }
                        
        if (Modifier.isPublic(method.getModifiers()) &&
            method.getReturnType().equals(boolean.class) &&
            method.getParameterTypes().length == 0 &&
            method.getName().matches("^is[A-Z].*")) {
        	return true;
        }            
        return false;
    }
	
	public static boolean isSetter(Method method) {
        return 
    		Modifier.isPublic(method.getModifiers()) &&
            method.getReturnType().equals(void.class) &&
            method.getParameterTypes().length == 1 &&
            method.getName().matches("^set[A-Z].*");
    }
}

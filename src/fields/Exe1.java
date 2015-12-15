package fields;

import java.lang.reflect.Field;

class Thing {
	private boolean flag;
	protected float f;
	public char c;
}

class MyThing extends Thing {
	private int num;
	protected String str;
	public double val;
}

public class Exe1 {
	public static void main(String[] args) {
		try {
			showPublicField(MyThing.class);
			
			System.out.println("--------------------");
			
			showAnyField(Thing.class);
			
			System.out.println("--------------------");
			
			showPublicFields(MyThing.class);
			
			System.out.println("--------------------");
			
			showAllFields(Thing.class);
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
	}
	
	static void showPublicField(Class<?> c) throws NoSuchFieldException {
		System.out.println("Public fields for class: " + c.getClass());
		Field field = c.getField("val"); // this is a public field on the class.
		System.out.println(field);
		field = c.getField("c"); // this is a public field on the parent class.
		System.out.println(field);
		//field = c.getField("blabla"); // this field does not exists, will throw Exception.
	}
	
	static void showAnyField(Class<?> c) throws NoSuchFieldException {
		System.out.println("\nAny Field for only class: " + c.getName());
		// get field of any visibility.
		Field field = c.getDeclaredField("flag");
		System.out.println(field);
	}

	static void showPublicFields(Class<?> c) {
        System.out.println("\nPublic Fields for class: " + c.getName());
        Field[] fields = c.getFields();
        for (Field field : fields)
            System.out.println(field); 
    }
	
	static void showAllFields(Class<?> c) {
        System.out.println("\nAll Fields for only class: "+ c.getName() );
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields)
            System.out.println(field); 
    }
}

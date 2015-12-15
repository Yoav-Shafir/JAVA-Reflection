package arrays;

/**
 *  one dimensional, primitive types.
 	[B -> byte
	[C -> char
	[D -> double
	[F -> float
	[I -> int
	[J -> long
	[S -> short
	[Z -> boolean
	
	// multidimensional
	[[Z -> two dimensions
	[[[Z -> three dimensions
	etc...
	
	class types
	String a1 = "[Ljava.lang.String;"; -> one dimension array of String.
	
	multidimensional
	[[Ljava.lang.String; -> 2 dim
	[[[Ljava.lang.String; -> 3 dim
 * 
 */

public class ArrayNotation {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// String array
		String name1 = "[Ljava.lang.String;";
		Class<?> c1 = Class.forName(name1);
		System.out.println(c1.getName());
		
		String name2 = "[[Ljava.lang.String;";
		Class<?> c2 = Class.forName(name2);
		System.out.println(c2.getName());
	
		// int array
		String name3 = "[I";
		Class<?> c3 = Class.forName(name3);
		System.out.println(c3.getName());
		
		// double array
		String name4 = "[I";
		Class<?> c4 = Class.forName(name4);
		System.out.println(c4.getName());
	}
}

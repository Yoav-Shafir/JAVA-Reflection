package objects.factory;

// using external config file and a Factory class
// to load class dynamically.
public class Main {
	public static void main(String[] args) {
		try {
			IDBase db = Factory.createDBase();
			db.write(new Record(100));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

package objects.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Factory {
	
	public static IDBase createDBase() throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, IOException {
		
		// get the properties file.
		Properties props = new Properties();
		InputStream file = new FileInputStream("config.properties");
		props.load(file);
		
		// get the class name to instantiate.
		String name = props.getProperty("DBase");
		file.close();
		return createObject(name);
	}

	@SuppressWarnings("unchecked")
	private static <T> T createObject(String className) throws ClassNotFoundException,
		InstantiationException, IllegalAccessException {
		
		// load the class.
		Class<?> c = Class.forName(className);
		return (T)c.newInstance();
	}
}
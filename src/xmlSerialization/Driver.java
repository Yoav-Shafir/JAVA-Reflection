package xmlSerialization;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 * serialize any instance to an xml structure.
 *
 */
public class Driver {
	public static Document serializeObject(Object source) throws Exception {
		
		return serializeHelper(source, 
								new Document(new Element("serialized")), 
								// 'IdentityHashMap' uses reference-equality in place of object-equality when comparing keys (and values)
								// table that stores references to all of the component objects to the argument. This table is used to ensure that
								// each component object is serialized only once no matter how many times it is referenced.
								new IdentityHashMap());
	}
	
	/**
	 * 
	 *
	 */
	private static <T> Document serializeHelper(T obj, Document target, Map<Object, String> table) throws Exception {
		
		// creates a unique identifier for object to be serialized.
		String id = Integer.toString(table.size()); 
		
		// add the instance to the table map.
		table.put(obj, id); 
		
		// get the instance Class object.
		Class<?> sourceclass = obj.getClass();
		
		// creates an XML element.
		Element objectElement = new Element("object"); 
		
		// set some attributes.
		objectElement.setAttribute("class", sourceclass.getName());
		objectElement.setAttribute("id", id);   
		
		// add the empty object XML element/tag to the main document as a first child.
		target.getRootElement().addContent(objectElement);    
		
		// handles non arrays instances.
		if (!sourceclass.isArray()) {
			
			 // get all this instance and its parent chain instance fields.
			 Field[] fields = Helper.getInstanceVariables(sourceclass);
			 for (int i = 0; i < fields.length; i++) {
				
				 Field curretField = fields[i];
				 
				 // permits access, if necessary.
				 if (!Modifier.isPublic(curretField.getModifiers())) {
					 curretField.setAccessible(true);
				 }
				 
				 // create a "field" XML element.
				 Element fieldElement = new Element("field"); // </field>
				 
				 // set the "name" attributes.
				 fieldElement.setAttribute("name", curretField.getName()); // </field name="SOME_FIELD_NAME">
				 
				 // get its declaring class.
				 Class<?> declClass = curretField.getDeclaringClass();
				 
				 // set the "declaringclass" attributes.
				 fieldElement.setAttribute("declaringclass", declClass.getName()); // </field name="SOME_FIELD_NAME" declaringclass="SOME_CLASS_NAME">
				 
				 // returns a Class object that identifies the declared type for the field represented by this Field object.
				 Class<?> fieldtype = curretField.getType();
				 
				 // returns the value of the field represented by this Field, on the specified object.
				 // obj is the actual instance.
				 Object child = curretField.get(obj); 
				 
				 if (Modifier.isTransient(curretField.getModifiers())) {
					 child = null;
				 }
				 
				 // append to the field element.
				 fieldElement.addContent(serializeVariable(fieldtype, child, target, table));
				 
				 // append to the main object element which is always a first child of the parent document.
				 objectElement.addContent(fieldElement);
			}
		}
		else {
			
			// returns the Class representing the component type of an array.
		    Class<?> componentType = sourceclass.getComponentType();               
		    int length = Array.getLength(obj);                      
		    
		    // set a "length" attribute on the object element.
		    objectElement.setAttribute("length", Integer.toString(length));   	                                                              
		    for (int i=0; i < length; i++) {                             
		    	objectElement.addContent(serializeVariable(componentType, Array.get(obj, i), target, table));          
		    }
		}
		return target;
	}
	
	/**
	 * 
	 *
	 */
	private static Element serializeVariable(Class<?> fieldtype, Object child, Document target, Map<Object, String> table) throws Exception {
		if (child == null) {
			return new Element("null");
		}
		
		// reference type.
		else if (!fieldtype.isPrimitive()) {
			
			// create a <reference> element which will hold a reference id
			// to object instance.
			Element reference = new Element("reference");
			
			// check if instance is already in table.
			if (table.containsKey(child)) {
				
				// get the id of this instance. this id is the value in the table/map. 
				reference.setText(table.get(child).toString());
		    }
		    else {
				reference.setText(Integer.toString(table.size()));
				
				// after creating the <reference> element which holds a reference id 
				// that points to the instance in the table map,
				// we pass the child/field object to the serializeHelper() method,
				// which again creates a new <object> tag.
				// all <object> tags are first childs of the root node.
				serializeHelper(child, target, table);
		    }
		    return reference;
		}
		
		// primitive type.
		else {
			Element value = new Element("value");
		    value.setText(child.toString());
		    return value;
		}
	}
	
	/**
	 *
	 */
	public static Object deserializeObject(Document source) throws Exception {
		List objList = source.getRootElement().getChildren();
		Map table = new HashMap();
		createInstances(table, objList);
		assignFieldValues(table, objList);
		return table.get("0");
	}
	
	/**
	 * 
	 *
	 */
	private static void createInstances(Map table, List objList) throws Exception {
		for (int i = 0; i < objList.size(); i++) {
			Element oElt = (Element) objList.get(i);
			
			// dynamically loads the class for each object.
			// query the document for the name of the class of a serialized object and loads that class.
			Class cls = Class.forName(oElt.getAttributeValue("class"));
			Object instance = null;
			if (!cls.isArray()) {
				
				// constructs an instance using reflective construction.
				Constructor c = cls.getDeclaredConstructor(null);
				if (!Modifier.isPublic(c.getModifiers())) {
				    c.setAccessible(true);
				}
				instance = c.newInstance(null);
			}
			else {
				// case array.
				// getComponentType - returns the Class representing the component type of an array.
				instance = Array.newInstance(cls.getComponentType(), Integer.parseInt(oElt.getAttributeValue("length")));
			}	
			table.put(oElt.getAttributeValue("id"), instance);
		}
	}
	
	/**
	 * 
	 * 
	 */
	private static void assignFieldValues(Map table, List objList) throws Exception {
		
		// iterate document objects.
		for (int i = 0; i < objList.size(); i++) {	
			Element objectElement = (Element) objList.get(i);
			
			// get the initialized instance from the table.
            Object instance = table.get(objectElement.getAttributeValue("id"));
            
            // get its children and iterate.
            List fieldElements = objectElement.getChildren();
            
            // handle non array.
            if (!instance.getClass().isArray()) {
            	for (int j=0; j<fieldElements.size(); j++) {
            		Element fieldElement = (Element) fieldElements.get(j);
            		String declaringclassName = fieldElement.getAttributeValue("declaringclass");
            		Class fieldDeclaringClass = Class.forName(declaringclassName);
            		String fieldName = fieldElement.getAttributeValue("name");
            		Field f = fieldDeclaringClass.getDeclaredField(fieldName);
            		if (!Modifier.isPublic(f.getModifiers())) {
                        f.setAccessible(true);
                    }
            		Element valueElement = (Element) fieldElement.getChildren().get(0);
            		f.set(instance, deserializeValue(valueElement, f.getType(), table));
            	}
            }
            else {
            	Class comptype = instance.getClass().getComponentType();
            	for ( int j = 0; j < fieldElements.size(); j++) {
            		Array.set(instance, j, deserializeValue((Element)fieldElements.get(j), comptype, table ));
            	}
            }
		}
	}
	
	/**
	 * 
	 * 
	 */
	private static Object deserializeValue(Element vElt, Class fieldType, Map table) throws ClassNotFoundException {
		String valtype = vElt.getName();
		if (valtype.equals("null")) {
		    return null;
		}
		else if (valtype.equals("reference")) {
		    return table.get(vElt.getText());
		}
		else {
		    if (fieldType.equals(boolean.class)) {
				if (vElt.getText().equals("true")) {
				    return Boolean.TRUE;
				}
				else {
				    return Boolean.FALSE;
				}
		    }
		    else if (fieldType.equals(byte.class)) {
		    	return Byte.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(short.class)) {
		    	return Short.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(int.class)) {
		    	return Integer.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(long.class)) {
		    	return Long.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(float.class)) {
		    	return Float.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(double.class)) {
		    	return Double.valueOf(vElt.getText());
		    }
		    else if (fieldType.equals(char.class)) {
		    	return new Character(vElt.getText().charAt(0));
		    }
		    else {
		    	return vElt.getText();
		    }
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		// serialize obj to xml.
//		try {
//			XMLOutputter out = new XMLOutputter();
//			Person p = new Person();
//			p.setName("Yoav");
//			p.setAge(38);
//			Document d = serializeObject(p);
//			out.output(d, System.out);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// deserialize xml to obj.
		// the SAXBuilder is the easiest way to create the JDOM2 objects.
        SAXBuilder jdomBuilder = new SAXBuilder();
        
        Document jdomDocument = jdomBuilder.build("/home/eryoav/dev/workspace_sts_java8/workspace/Reflection/src/xmlSerialization/Person.xml");
        deserializeObject(jdomDocument);
	}
}

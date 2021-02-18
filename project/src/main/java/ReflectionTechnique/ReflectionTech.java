package ReflectionTechnique;

import java.util.Vector;

import com.mysql.cj.core.result.Field;

public class ReflectionTech {

	public static Vector<Object> retrieveProperties(Object object) 
	{
		Vector<Object> data = new Vector<Object>();
		for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) 
		{
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				data.add(value);
				System.out.println(field.getName() + "=" + value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		
		return data;
	}
	
	public static Vector<String> retrieveHeader(Object object) 
	{
		Vector<String> data = new Vector<String>();
		for (java.lang.reflect.Field field : object.getClass().getDeclaredFields()) 
		{
			field.setAccessible(true); // set modifier to public
			try {
				data.add(field.getName());

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		}
		
		return data;
	}
}

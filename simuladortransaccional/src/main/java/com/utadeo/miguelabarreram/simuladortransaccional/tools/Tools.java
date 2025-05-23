package com.utadeo.miguelabarreram.simuladortransaccional.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Tools {
	
	public String arrToString(Collection<?> array) {
		StringBuilder string = new StringBuilder("[");
		if (null != array)
			for (Object obj : array)
				string.append(((null != obj) ? obj.toString() : "{}") + ",");
		string.append("]");
		return string.toString();
	}

	public String generateAleatoryText(int length) {
		StringBuilder string = new StringBuilder("");
		while (length != string.length()) {
			int caracter = (int) (Math.random() * 123);
			if ((caracter > 32 && caracter < 91) || (caracter > 96 && caracter < 123))
				string.append(String.valueOf((char) caracter));
		}
		return string.toString();
	}

	public Object castBodyToEntity(Class<?> c, Map<String, String> map) throws InstantiationException, IllegalAccessException{
		@SuppressWarnings("deprecation")
		Object obj = c.newInstance();

		map.keySet().forEach((String key) -> {
			try {
				Field f = c.getDeclaredField(key);
				String setter = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				Method m = c.getDeclaredMethod(setter, f.getType());
				
				switch (f.getType().getSimpleName()) {
					case "int":
						m.invoke(obj, Integer.valueOf(map.get(key)));
						break;
					case "String":
						m.invoke(obj, map.get(key));
						break;
					case "Timestamp":
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
						m.invoke(obj, new Timestamp(sdf.parse(map.get(key)).getTime()));
						break;
					case "float": 
						m.invoke(obj, Float.valueOf(map.get(key)));
						break;
					case "double": 
						m.invoke(obj, Double.valueOf(map.get(key)));
						break;
				}
			} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException e) {
				e.printStackTrace();
			}
		});
		return obj;
	}
}

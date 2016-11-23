package com.osori.hyson;
import org.json.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by YongEun on 2016-11-16.
 */
public class HySON {
    public static <T> T parse(String jsonString, Class<T> parseClass) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        
        return parseJSON(jsonObject, parseClass);
    }

	public static <T> T parseJSON(JSONObject jsonObject, Class<T> parseClass) {
		Object instance = null;
		
        try {
            Constructor<T> constructor = parseClass.getConstructor();
            instance = constructor.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Iterator<?> keys = jsonObject.keys();

        while(keys.hasNext() == true) {
            String key = (String)keys.next();
            Field field = null;

            try {
                field = parseClass.getField(key);
                Class<?> fieldType = field.getType();
                if(field.isAccessible() == false)
                    field.setAccessible(true);

                if(fieldType.isArray() == true) {
                    JSONArray jsonArrayObject = jsonObject.getJSONArray(key);
                    Class<?> elementType = fieldType.getComponentType();
                    Object values = Array.newInstance(elementType, jsonArrayObject.length());
                    // TODO : field int - data float 어떻게?
                    if (elementType.isPrimitive() == true) {
                    	if(elementType == float.class || elementType == Float.class)
                    	{
                    		for(int i = 0; i < jsonArrayObject.length(); ++i) {
	                            Array.set(values, i, elementType.cast(jsonArrayObject.get(i)));
	                        }
                    	}
                    	else
                    	{
	                    	for(int i = 0; i < jsonArrayObject.length(); ++i) {
	                            Array.set(values, i, jsonArrayObject.get(i));
	                        }
                    	}
                    }
                    else
                    {
                    	if(isParsableElementType(elementType))
                    	{
                    		for(int i = 0; i < jsonArrayObject.length(); ++i)
                    		{
                                Array.set(values, i, jsonArrayObject.get(i));
                            }
                    	}
                    	else
                    	{
                    		for(int i = 0; i < jsonArrayObject.length(); ++i)
                    		{
                    			Object elementObject = parseJSON(jsonArrayObject.getJSONObject(i), elementType);
                                Array.set(values, i, elementObject);
                            }
                    	}
                    }

                    field.set(instance, values);
                }
                else
                {
                    // TODO : field int - data float 어떻게?
                    if (fieldType.isPrimitive() == true)
                    {
                    	if(fieldType == float.class)
                    	{
                    		field.set(instance, ((Double)jsonObject.get(key)).floatValue());
                    	}
                    	else
                    	{
                    		field.set(instance, jsonObject.get(key));
                    	}
                    }
                    else
                    {
                    	if(isParsableElementType(fieldType) == true)
                    	{
	                    	if(fieldType == Float.class)
	                    	{
	                    		field.set(instance, ((Double)jsonObject.get(key)).floatValue());
	                    	}
	                    	else
	                    	{
	                    		Object value = fieldType.cast(jsonObject.get(key));
	                    		field.set(instance, fieldType.cast(value));
	                    	}
                    	}
                    	else if(isCollectionType(fieldType) == true)
                    	{
                    		if(fieldType == ArrayList.class)
                    		{
                    			Member member = field.getAnnotation(Member.class);
                    			Class<?> elementType = member.value();
                    			Object arrayList = CreateArrayList(elementType);
                    			Method addMethod = ArrayList.class.getMethod("add", Object.class);
                    			
                    			JSONArray jsonArrayObject = jsonObject.getJSONArray(key);
                                // TODO : field int - data float 어떻게?
                                if (elementType.isPrimitive() == true) {
                                	if(elementType == float.class || elementType == Float.class)
                                	{
                                		for(int i = 0; i < jsonArrayObject.length(); ++i) {
                                			addMethod.invoke(arrayList, elementType.cast(jsonArrayObject.get(i)));
            	                        }
                                	}
                                	else
                                	{
            	                    	for(int i = 0; i < jsonArrayObject.length(); ++i) {
            	                    		addMethod.invoke(arrayList, jsonArrayObject.get(i));
            	                        }
                                	}
                                }
                                else
                                {
                                	if(isParsableElementType(elementType))
                                	{
                                		for(int i = 0; i < jsonArrayObject.length(); ++i)
                                		{
                                			addMethod.invoke(arrayList, jsonArrayObject.get(i));
                                        }
                                	}
                                	else
                                	{
                                		for(int i = 0; i < jsonArrayObject.length(); ++i)
                                		{
                                			Object elementObject = parseJSON(jsonArrayObject.getJSONObject(i), elementType);
                                			addMethod.invoke(arrayList, elementObject);
                                        }
                                	}
                                }

                                field.set(instance, arrayList);
                    		}
                    	}
                    	else
                    	{
                    		JSONObject elementJsonObject = jsonObject.getJSONObject(key);
                    		Object elementObject = parseJSON(elementJsonObject, fieldType);
                    		field.set(instance, elementObject);
                    	}
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return parseClass.cast(instance);
	}

	private static <T> ArrayList<T> CreateArrayList(Class<T> memberType) {
		return new ArrayList<T>();
	}

	private static boolean isCollectionType(Class<?> fieldType) {
		if(fieldType == ArrayList.class)
			return true;
		
		return false;
	}

	private static boolean isParsableElementType(Class<?> elementType) {
		if(elementType.isPrimitive())
			return true;
		
		if(elementType == Integer.class ||
			elementType == Float.class ||
			elementType == Double.class ||
			elementType == Long.class ||
			elementType == Boolean.class ||
			elementType == String.class)
			return true;
		
		return false;
	}
}

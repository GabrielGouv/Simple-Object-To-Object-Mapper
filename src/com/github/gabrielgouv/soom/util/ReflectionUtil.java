package com.github.gabrielgouv.soom.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ReflectionUtil {
	
	public static List<Field> getAllFields(Object object) {
		return getAllFields(object.getClass());
	}

	public static List<Field> getAllFields(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		return new ArrayList<>(Arrays.asList(fields));
	}
	
	public static List<Method> getAllMethods(Class<?> clazz) {
		return new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
	}
	
	public static List<Method> getAllGetters(Object object) {
		return getAllGetters(object.getClass());
	}
	
	public static List<Method> getAllGetters(Class<?> clazz) {
		return getAllMethodsStartingWith(clazz, "get");
	}
	
	public static List<Method> getAllSetters(Object object) {
		return getAllSetters(object.getClass());
	}
	
	public static List<Method> getAllSetters(Class<?> clazz) {
		return getAllMethodsStartingWith(clazz, "set");
	}
	
	public static List<Method> getAllMethodsStartingWith(Class<?> clazz, String name) {
		return getAllMethods(clazz)
				.stream()
				.filter(m -> m.getName().startsWith(name))
				.collect(Collectors.toList());
	}
	
	public static List<Method> getMethodsThatContain(Class<?> clazz, String name) {
		return getAllMethods(clazz)
				.stream()
				.filter(m -> m.getName().contains(name))
				.collect(Collectors.toList());
	}
	
	public static Method getGetter(Class<?> clazz, String fieldName) {
		return getMethodByName(clazz, "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
	}
	
	public static <T> Method getSetter(Class<?> clazz, String fieldName, T parameter) {
		return getMethodByName(clazz, "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), parameter.getClass());
	}
	
	public static Method getMethodByName(Class<?> clazz, String name, Class<?> ... parameters) {
		try {
			if (parameters.length > 0) {
				return clazz.getMethod(name, parameters);
			} else {
				return clazz.getMethod(name);
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T instantiateClass(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> void invokeSetterByFieldName(T object, String fieldName, Object parameter) {
		invokeMethodAndGetReturn(object, getSetter(object.getClass(), fieldName, parameter), parameter);
	}
	
	public static Object invokeGetterByFieldName(Object object, String fieldName) {
		return invokeMethodAndGetReturn(object, getGetter(object.getClass(), fieldName));
	}
		
	public static Object invokeMethodAndGetReturn(Object object, Method method, Object ... parameters) {
		try {
			if (parameters.length > 0) {
				return method.invoke(object, parameters);
			} else {
				return method.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

package com.github.gabrielgouv.soom.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ReflectionUtil {
	
	public static List<Field> getFields(Object object) {
		return getFields(object.getClass());
	}

	public static List<Field> getFields(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<Field> fieldList = new ArrayList<>(Arrays.asList(fields));
		fieldList.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
		return fieldList;
	}
	
	public static List<Method> getMethods(Object object) {
		return getMethods(object.getClass());
	}
	
	public static List<Method> getMethods(Class<?> clazz) {
		List<Method> methods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
		methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
		return methods;
	}
	
	public static List<Method> getGetters(Object object) {
		return getGetters(object.getClass());
	}
	
	public static List<Method> getGetters(Class<?> clazz) {
		return getAllMethodsStartingWith(clazz, "get");
	}
	
	public static List<Method> getSetters(Object object) {
		return getSetters(object.getClass());
	}
	
	public static List<Method> getSetters(Class<?> clazz) {
		return getAllMethodsStartingWith(clazz, "set");
	}
	
	public static List<Method> getGettersAndSetters(Object object) {
		return getGettersAndSetters(object.getClass());
	}
	
	public static List<Method> getGettersAndSetters(Class<?> clazz) {
		List<Method> methods = new ArrayList<>();
		methods.addAll(getGetters(clazz));
		methods.addAll(getSetters(clazz));
		methods.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
		return methods;
	}
	
	public static List<String> getFieldNamesFromGettersAndSetters(Object object) {
		return getFieldNamesFromGettersAndSetters(object.getClass());
	}
	
	public static List<String> getFieldNamesFromGettersAndSetters(Class<?> clazz) {
		
		List<String> names = new ArrayList<>();
		List<Method> getters = getGetters(clazz);
		List<Method> setters = getSetters(clazz);

		getters.stream().map(Method::getName).forEach(getterName -> {
			setters.stream().map(Method::getName).forEach(setterName -> {
				String nameWithoutGet = StringUtil.removeGet(getterName);
				String nameWithoutSet = StringUtil.removeSet(setterName);
				if (nameWithoutGet.equals(nameWithoutSet)) {
					names.add(StringUtil.uncapitalize(nameWithoutGet));
				}
			});
		});
		
		names.sort((s1, s2) -> s1.compareTo(s2));

		return names;
	}
	
	public static List<Method> getAllMethodsStartingWith(Object object, String name) {
		return getAllMethodsStartingWith(object.getClass(), name);
	}
	
	public static List<Method> getAllMethodsStartingWith(Class<?> clazz, String name) {
		return getMethods(clazz)
				.stream()
				.filter(m -> m.getName().startsWith(name))
				.sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
				.collect(Collectors.toList());
	}
	
	public static List<Method> getMethodsThatContain(Object object, String name) {
		return getMethodsThatContain(object.getClass(), name);
	}
	
	public static List<Method> getMethodsThatContain(Class<?> clazz, String name) {
		return getMethods(clazz)
				.stream()
				.filter(m -> m.getName().contains(name))
				.sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
				.collect(Collectors.toList());
	}
	
	public static Method getGetterByFieldName(Object object, String fieldName) {
		return getGetterByFieldName(object.getClass(), fieldName);
	}
	
	public static Method getGetterByFieldName(Class<?> clazz, String fieldName) {
		return getMethodByName(clazz, "get" + StringUtil.capitalize(fieldName));
	}
	
	public static <T> Method getSetterByFieldName(Object object, String fieldName, T parameter) {
		return getSetterByFieldName(object.getClass(), fieldName, parameter);
	}
	
	public static <T> Method getSetterByFieldName(Class<?> clazz, String fieldName, T parameter) {
		return getMethodByName(clazz, "set" + StringUtil.capitalize(fieldName), parameter.getClass());
	}
	
	public static Method getMethodByName(Object object, String name, Class<?> ... parameters) {
		return getMethodByName(object.getClass(), name, parameters);
	}
	
	public static Method getMethodByName(Class<?> clazz, String name, Class<?> ... parameters) {
		try {
			if (parameters.length > 0) {
				return clazz.getMethod(name, parameters);
			} else {
				return clazz.getMethod(name);
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			//e.printStackTrace();
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
	
	public static Object invokeSetterByFieldName(Object object, String fieldName, Object parameter) {
		return invokeMethodAndGetReturn(object, getSetterByFieldName(object.getClass(), fieldName, parameter), parameter);
	}
	
	public static Object invokeGetterByFieldName(Object object, String fieldName) {
		return invokeMethodAndGetReturn(object, getGetterByFieldName(object.getClass(), fieldName));
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
	
	public static Object getTypeFromGetterByFieldName(Object object, String fieldName) {
		return getTypeFromGetterByFieldName(object.getClass(), fieldName);
	}
	
	public static Object getTypeFromGetterByFieldName(Class<?> clazz, String fieldName) {
		return getMethodByName(clazz, "get" + StringUtil.capitalize(fieldName)).getReturnType();
	}
	
}

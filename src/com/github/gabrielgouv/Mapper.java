package com.github.gabrielgouv;

import java.lang.reflect.Field;
import java.util.List;

import com.github.gabrielgouv.util.ReflectionUtil;

public class Mapper implements IMapper {

	private Object object;
	private Class<?> clazz;
	
	@Override
	public IMapper map(Object object) {
		this.object = object;
		return this;
	}

	@Override
	public <T> T to(Class<T> clazz) {
		this.clazz = clazz;
		return convertTo(clazz);
	}
	
	private <T> T convertTo(Class<T> clazz) {
		List<Field> objectFields = ReflectionUtil.getAllFields(this.object);
		List<Field> clazzFields = ReflectionUtil.getAllFields(clazz);
		
		T instance = ReflectionUtil.instantiateClass(clazz);
		
		objectFields.forEach(objectField -> {
			
			clazzFields.forEach(clazzField -> {
				if (objectField.getName().equals(clazzField.getName())) {
															
					ReflectionUtil.invokeSetterByFieldName(instance, clazzField.getName(), 
							ReflectionUtil.invokeGetterByFieldName(this.object, objectField.getName()));
					
				}
			});
			
		});
			
		return instance;
	}
	
}
package com.github.gabrielgouv.soom.core.converter;

import java.lang.reflect.Field;
import java.util.List;

import com.github.gabrielgouv.soom.util.ReflectionUtil;

public class StandardConverter implements IConverter {

	@Override
	public <T> T convert(Object source, Class<T> destinantion) {

		T destinationInstance = ReflectionUtil.instantiateClass(destinantion);

		List<Field> sourceFields = ReflectionUtil.getAllFields(source);
		List<Field> destinationFields = ReflectionUtil.getAllFields(destinationInstance);

		sourceFields.forEach(sourceField -> {
			destinationFields.forEach(destinationField -> {
				transferValue(source, destinationInstance, sourceField, destinationField);
			});
		});

		return destinationInstance;
		
	}

	private <T> void transferValue(Object source, T instance, Field sourceField, Field destinationField) {
		
		if (sourceField.getName().equals(destinationField.getName())) {
			Object objectFieldValue = ReflectionUtil.invokeGetterByFieldName(source, sourceField.getName());
			ReflectionUtil.invokeSetterByFieldName(instance, destinationField.getName(), objectFieldValue);
		}
		
	}

}

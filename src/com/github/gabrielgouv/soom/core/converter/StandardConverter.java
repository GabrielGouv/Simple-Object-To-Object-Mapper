package com.github.gabrielgouv.soom.core.converter;

import java.util.List;

import com.github.gabrielgouv.soom.util.ReflectionUtil;
import com.github.gabrielgouv.soom.util.StringUtil;

public class StandardConverter implements IConverter {

	@Override
	public <T> T convert(Object source, Class<T> destination) {

		T destinationInstance = ReflectionUtil.instantiateClass(destination);
		process(source, destinationInstance);
		
		return destinationInstance;
		
	}
	
	private void process(Object source, Object destination) {
		List<String> fieldNamesFromSource = ReflectionUtil.getFieldNamesFromGettersAndSetters(source);
		List<String> fieldNamesFromDestination = ReflectionUtil.getFieldNamesFromGettersAndSetters(destination);
		
		for (String nameFromDestination : fieldNamesFromDestination) {
			for (String nameFromSource : fieldNamesFromSource) {
				
				if (nameFromDestination.equals(nameFromSource)) {
					transferValue(source, destination, nameFromSource, nameFromDestination);
					break;
				} else {
					
					String className = source.getClass().getSimpleName().toLowerCase();
	
					if (nameFromDestination.startsWith(className) && nameFromDestination.substring(className.length()).equalsIgnoreCase(nameFromSource)) {
						transferValue(source, destination, nameFromSource, nameFromDestination);
						break;
					} else {
						
						Object returnValue = ReflectionUtil.invokeGetterByFieldName(source, nameFromSource);
						className = StringUtil.uncapitalize(returnValue.getClass().getSimpleName());
						
						if (nameFromDestination.startsWith(className)) {
							Object newSource = ReflectionUtil.invokeGetterByFieldName(source, nameFromSource);
							process(newSource, destination);
							break;
						}
						
					}
					
				}
				
			}
		}
	}
	
	private void transferValue(Object source, Object destination, String sourceFieldName, String destinationFieldName) {
		Object value = ReflectionUtil.invokeGetterByFieldName(source, sourceFieldName);
		ReflectionUtil.invokeSetterByFieldName(destination, destinationFieldName, value);
	}

}

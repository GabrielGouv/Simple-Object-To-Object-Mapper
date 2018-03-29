package com.github.gabrielgouv.soom.core.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.github.gabrielgouv.soom.core.WrappedObject;
import com.github.gabrielgouv.soom.util.ReflectionUtil;

public final class WrappedObjectFactory implements IObjectFactory<WrappedObject> {

	@Override
	public WrappedObject create(Object object) {
		
		WrappedObject wrappedObject = new WrappedObject();
		List<Method> gettersAndSetters = ReflectionUtil.getGettersAndSetters(object);
		//List<Field>
		
		wrappedObject.setInstance(object);
		wrappedObject.setMethods(gettersAndSetters);
		
		return null;
	}

	@Override
	public WrappedObject create(Class<?> clazz) {
		
		return null;
	}

	
	
}

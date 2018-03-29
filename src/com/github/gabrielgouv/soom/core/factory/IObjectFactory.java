package com.github.gabrielgouv.soom.core.factory;

public interface IObjectFactory<T> {

	T create(Object object);
	T create(Class<?> clazz);
	
}

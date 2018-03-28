package com.github.gabrielgouv;

public interface IMapper {

	IMapper map(Object object);
	<T> T to(Class<T> clazz);
	
}

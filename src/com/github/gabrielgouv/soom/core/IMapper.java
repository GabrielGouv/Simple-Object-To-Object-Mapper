package com.github.gabrielgouv.soom.core;

import com.github.gabrielgouv.soom.core.converter.IConverter;

public interface IMapper {

	IMapper map(Object object);
	<T> T to(Class<T> clazz);
	IMapper usingConverter(IConverter converter);
	
}

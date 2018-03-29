package com.github.gabrielgouv.soom.core.converter;

public interface IConverter {

	<T> T convert(Object object, Class<T> destinantion);
	
}

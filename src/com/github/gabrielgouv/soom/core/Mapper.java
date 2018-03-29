package com.github.gabrielgouv.soom.core;

import com.github.gabrielgouv.soom.core.converter.IConverter;
import com.github.gabrielgouv.soom.core.converter.StandardConverter;

public class Mapper implements IMapper {

	private Object source;
	private IConverter converter;
	
	public Mapper() {
		this.converter = new StandardConverter();
	}
	
	public Mapper(IConverter defaultConverter) {
		this.converter = defaultConverter;
	}
	
	@Override
	public IMapper map(Object source) {
		this.source = source;
		return this;
	}

	@Override
	public <T> T to(Class<T> destination) {
		return convertTo(destination);
	}
	
	@Override
	public IMapper usingConverter(IConverter converter) {
		this.converter = converter;
		return this;
	}
	
	private <T> T convertTo(Class<T> clazz) {
		return this.converter.convert(this.source, clazz);
	}
	
}
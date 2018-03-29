package com.github.gabrielgouv.soom.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class WrappedObject {

	private Object instance;
	private List<Field> fields;
	private List<Method> methods;

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

}

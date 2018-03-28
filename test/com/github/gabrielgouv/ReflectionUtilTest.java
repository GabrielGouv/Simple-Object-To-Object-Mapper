package com.github.gabrielgouv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.gabrielgouv.builder.ClientBuilder;
import com.github.gabrielgouv.model.Client;
import com.github.gabrielgouv.util.ReflectionUtil;

public class ReflectionUtilTest {

	private Client client;
	private List<String> clientFieldNames;
	
	@Before
	public void configure() {		
		client = new ClientBuilder()
				.withId(1)
				.withEmail("test@test.com")
				.withName("Test")
				.withAddress(null)
				.build();
		
		clientFieldNames = new ArrayList<>(Arrays.asList("id", "name", "email", "address"));
	}
	
	@Test
	public void getAllFieldsTest() {
		List<Field> fields = ReflectionUtil.getAllFields(client);
		assertEquals(fields.size(), clientFieldNames.size());
	}
	
	@Test
	public void getAllGettersTest() {
		List<Method> methods = ReflectionUtil.getAllGetters(client);
		methods.stream().map(Method::getName).forEach(System.out::println);
		assertEquals(methods.size(), clientFieldNames.size());
	}
	
	@Test
	public void getAllSettersTest() {
		List<Method> methods = ReflectionUtil.getAllSetters(client);
		methods.stream().map(Method::getName).forEach(System.out::println);
		assertEquals(methods.size(), clientFieldNames.size());
	}
	
}

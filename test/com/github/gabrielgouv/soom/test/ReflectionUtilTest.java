package com.github.gabrielgouv.soom.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.gabrielgouv.soom.builder.ClientBuilder;
import com.github.gabrielgouv.soom.model.Client;
import com.github.gabrielgouv.soom.util.ReflectionUtil;

public class ReflectionUtilTest {

	private Client client;
	private List<String> clientFieldNames;
	private List<String> clientGettersNames;
	private List<String> clientSettersNames;
	private List<String> clientMethodNames;
	private List<String> clientGettersSettersNames;
	
	@Before
	public void configure() {		
		client = new ClientBuilder()
				.withId(1)
				.withEmail("test@test.com")
				.withName("Test")
				.withAddress(null)
				.build();
		
		clientFieldNames = new ArrayList<>(Arrays.asList("id", "name", "email", "address"));
		clientGettersNames = new ArrayList<>(Arrays.asList("getId", "getName", "getEmail", "getAddress"));
		clientSettersNames = new ArrayList<>(Arrays.asList("setId", "setName", "setEmail", "setAddress"));
		clientMethodNames = new ArrayList<>();
		clientMethodNames.addAll(clientGettersNames);
		clientMethodNames.addAll(clientSettersNames);
		clientGettersSettersNames = new ArrayList<>();
		clientGettersSettersNames.addAll(clientGettersNames);
		clientGettersSettersNames.addAll(clientSettersNames);
		clientFieldNames.sort((s1, s2) -> s1.compareTo(s2));
		clientGettersNames.sort((s1, s2) -> s1.compareTo(s2));
		clientSettersNames.sort((s1, s2) -> s1.compareTo(s2));
		clientMethodNames.sort((s1, s2) -> s1.compareTo(s2));
		clientGettersSettersNames.sort((s1, s2) -> s1.compareTo(s2));
	}
	
	@Test
	public void getFieldsUsingObjectTest() {
		List<Field> fields = ReflectionUtil.getFields(client);
		List<String> fieldNames = fields.stream().map(Field::getName).collect(Collectors.toList());
		Assert.assertThat(clientFieldNames, IsIterableContainingInOrder.contains(fieldNames.toArray()));
	}
	
	@Test
	public void getMethodsUsingObjectTest() {
		List<Method> methods = ReflectionUtil.getMethods(client);
		List<String> names = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientMethodNames, IsIterableContainingInOrder.contains(names.toArray()));
	}
	
	@Test
	public void getGettersTest() {
		List<Method> methods = ReflectionUtil.getGetters(client);
		List<String> getterNames = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientGettersNames, IsIterableContainingInOrder.contains(getterNames.toArray()));
	}
	
	@Test
	public void getSettersTest() {
		List<Method> methods = ReflectionUtil.getSetters(client);
		List<String> setterNames = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientSettersNames, IsIterableContainingInOrder.contains(setterNames.toArray()));
	}
	
	@Test
	public void getGettersAndSettersTest() {
		List<Method> methods = ReflectionUtil.getGettersAndSetters(client);
		List<String> names = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientGettersSettersNames, IsIterableContainingInOrder.contains(names.toArray()));
	}
	
	@Test
	public void getFieldNamesFromGettersAndSettersTest() {
		List<String> names = ReflectionUtil.getFieldNamesFromGettersAndSetters(client);
		Assert.assertThat(clientFieldNames, IsIterableContainingInOrder.contains(names.toArray()));
	}
	
	@Test
	public void getAllMethodsStartingWithTest() {
		List<Method> methods = ReflectionUtil.getAllMethodsStartingWith(client, "get");
		List<String> names = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientGettersNames, IsIterableContainingInOrder.contains(names.toArray()));
	}
	
	@Test
	public void getMethodsThatContainTest() {
		List<Method> methods = ReflectionUtil.getMethodsThatContain(client, "get");
		List<String> names = methods.stream().map(Method::getName).collect(Collectors.toList());
		Assert.assertThat(clientGettersNames, IsIterableContainingInOrder.contains(names.toArray()));
	}
	
	@Test
	public void getGetterByFieldNameTest() {
		Method method = ReflectionUtil.getGetterByFieldName(client, "name");
		assertEquals("getName", method.getName());
	}
	
	@Test
	public void getSetterByFieldNameTest() {
		Method method = ReflectionUtil.getSetterByFieldName(client, "id", int.class);
		assertEquals("setId", method.getName());
	}
	
	@Test
	public void getMethodByNameUsingValidNameTest() {
		Method method = ReflectionUtil.getMethodByName(client, "getEmail");
		assertNotNull(method);
		assertEquals("getEmail", method.getName());
	}
	
	@Test
	public void getMethodByNameUsingInvalidNameTest() {
		Method method = ReflectionUtil.getMethodByName(client, "getEmail");
		assertNull(method);
	}
	
	@Test
	public void instantiateClassTest() {
		Client c = ReflectionUtil.instantiateClass(Client.class);
		assertNotNull(c);
	}
	
	@Test
	public void invokeSetterByFieldNameTest() {
		ReflectionUtil.invokeSetterByFieldName(client, "name", "Pedro");
		assertEquals("Pedro", client.getName());
	}
	
	@Test
	public void invokeGetterByFieldNameTest() {
		String name = (String) ReflectionUtil.invokeGetterByFieldName(client, "name");
		assertEquals("Test", name);
	}
	
	@Test
	public void invokeMethodAndGetReturn() {
		int id = (int) ReflectionUtil.invokeMethodAndGetReturn(client, 
				ReflectionUtil.getGetterByFieldName(client, "id"));
		assertEquals(1, id);
	}
	
}

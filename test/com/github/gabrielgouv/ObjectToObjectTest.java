package com.github.gabrielgouv;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.gabrielgouv.builder.AddressBuilder;
import com.github.gabrielgouv.builder.ClientBuilder;
import com.github.gabrielgouv.dto.ClientDto;
import com.github.gabrielgouv.model.Client;

public class ObjectToObjectTest {

	private Client client;
	
	@Before
	public void configure() {
		client = new ClientBuilder()
				.withId(1)
				.withName("João")
				.withEmail("test@test.com")
				.withAddress(new AddressBuilder()
						.withId(1)
						.withCity("Recife")
						.withStreet("St. Test")
						.withNumber("252")
						.build())
				.build();
	}
	
	@Test
	public void convertObjectToObject() {
		
		Mapper mapper = new Mapper();
		ClientDto dto = mapper.map(client).to(ClientDto.class);
		
		assertEquals(client.getId(), dto.getId());
		assertEquals(client.getName(), dto.getName());
		assertEquals(client.getEmail(), dto.getEmail());
		
	}
	
}

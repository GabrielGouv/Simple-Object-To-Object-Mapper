package com.github.gabrielgouv.builder;

import com.github.gabrielgouv.model.Address;
import com.github.gabrielgouv.model.Client;

public class ClientBuilder {

	private int id;
	private String name;
	private String email;
	private Address address;

	public ClientBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public ClientBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ClientBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public ClientBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}
	
	public Client build() {
		return new Client(id, name, email, address);
	}

}

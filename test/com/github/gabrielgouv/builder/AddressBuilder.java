package com.github.gabrielgouv.builder;

import com.github.gabrielgouv.model.Address;

public class AddressBuilder {

	private int id;
	private String city;
	private String street;
	private String number;

	public AddressBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public AddressBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	public AddressBuilder withStreet(String street) {
		this.street = street;
		return this;
	}

	public AddressBuilder withNumber(String number) {
		this.number = number;
		return this;
	}
	
	public Address build() {
		return new Address(id, city, street, number);
	}

}

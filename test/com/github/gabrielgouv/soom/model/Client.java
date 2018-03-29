package com.github.gabrielgouv.soom.model;

public class Client {

	private Integer id;
	private String name;
	private String email;
	private Address address;

	public Client(int id, String name, String email, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address product) {
		this.address = product;
	}

}

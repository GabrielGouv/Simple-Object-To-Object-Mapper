package com.github.gabrielgouv.soom.dto;

public class ClientDto {

	private int clientId;
	private String name;
	private String email;
	private int addressId;
	private String city;
	private String addressStreet;
	private String number;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer id) {
		this.clientId = id;
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String street) {
		this.addressStreet = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

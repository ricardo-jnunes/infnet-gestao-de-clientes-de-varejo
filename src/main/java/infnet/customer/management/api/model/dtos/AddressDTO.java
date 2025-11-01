package infnet.customer.management.api.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class AddressDTO {

	private Integer id;

	@NotBlank(message = "O CEP é obrigatório.")
	private String cep;

	@NotBlank(message = "A rua é obrigatória.")
	private String street;

	private Integer number;

	@NotBlank(message = "O bairro é obrigatório.")
	private String neighborhood;

	@NotBlank(message = "O estado é obrigatório.")
	private String state;

	@NotBlank(message = "O país é obrigatório.")
	private String country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}

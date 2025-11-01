package infnet.customer.management.api.model.dtos;

import java.util.List;

import infnet.customer.management.api.model.domain.Person.Type;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDTO {

	private Integer id;

	@NotBlank(message = "O nome é obrigatório.")
	private String name;

	@NotBlank(message = "O documento é obrigatório.")
	private String document;

	private Type type;

	@Email(message = "E-mail inválido.")
	@NotBlank(message = "O e-mail é obrigatório.")
	private String email;

	@NotBlank(message = "O telefone é obrigatório.")
	private String phone;

	@NotNull(message = "O campo 'active' é obrigatório.")
	private Boolean active;

	@Valid
	private List<AddressDTO> addresses;

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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

}

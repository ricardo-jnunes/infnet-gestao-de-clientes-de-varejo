package infnet.customer.management.api.model.domain;

public class Customer {

	private Integer id;
	private String name;
	private String document;
	private String email;
	private String phone;
	private Type type;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		CPF, CNPJ;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", document=" + document + ", email=" + email + ", phone="
				+ phone + ", type=" + type + "]";
	}

}

package infnet.customer.management.api.model.domain;

public abstract class Person {

	private Integer id;
	private String name;
	private String document;
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
		return "Person [id=" + id + ", name=" + name + ", document=" + document + ", type=" + type + "]";
	}

}

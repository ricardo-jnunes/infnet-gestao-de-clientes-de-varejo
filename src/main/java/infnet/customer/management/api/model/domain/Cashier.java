package infnet.customer.management.api.model.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Cashier extends Person {

	@NotNull(message = "A matrícula é obrigatória.")
	private String registry;
	private String email;
	private String phone;
	private Boolean active;
	@NotNull(message = "O salário é obrigatório.")
	@Min(value = 0, message = "O salário não pode ser negativo e maior que R$ 0,00.")
	private Double salary;

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + " - Cashier [registry=" + registry + ", email=" + email + ", phone=" + phone
				+ ", active=" + active + ", salary=" + salary + "]";
	}

}

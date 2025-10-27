package infnet.customer.management.api.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;

@Entity
public class Customer extends Person {

	private String email;
	private String phone;
	private Boolean active;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@Valid
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return super.toString() + " - Customer [email=" + email + ", phone=" + phone + ", active=" + active
				+ ", address=" + address + "]\n";
	}

}

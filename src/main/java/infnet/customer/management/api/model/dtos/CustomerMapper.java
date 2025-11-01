package infnet.customer.management.api.model.dtos;

import java.util.stream.Collectors;

import infnet.customer.management.api.model.domain.Address;
import infnet.customer.management.api.model.domain.Customer;

public class CustomerMapper {

	public static CustomerDTO toDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setDocument(customer.getDocument());
		dto.setType(customer.getType());
		dto.setEmail(customer.getEmail());
		dto.setPhone(customer.getPhone());
		dto.setActive(customer.isActive());
		if (customer.getAddresses() != null) {
			dto.setAddresses(customer.getAddresses().stream().map(CustomerMapper::toDTO).
					collect(Collectors.toList()));
		}
		return dto;
	}

	public static AddressDTO toDTO(Address address) {
		AddressDTO dto = new AddressDTO();
		dto.setId(address.getId());
		dto.setCep(address.getCep());
		dto.setStreet(address.getStreet());
		dto.setNumber(address.getNumber());
		dto.setNeighborhood(address.getNeighborhood());
		dto.setState(address.getState());
		dto.setCountry(address.getCountry());
		return dto;
	}

	public static Customer toEntity(CustomerDTO dto) {
		Customer customer = new Customer();
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setDocument(dto.getDocument());
		customer.setType(dto.getType());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getPhone());
		customer.setActive(dto.getActive());
		if (dto.getAddresses() != null) {
			customer.setAddresses(dto.getAddresses().stream().map(a -> {
				Address address = new Address();
				address.setCep(a.getCep());
				address.setStreet(a.getStreet());
				address.setNumber(a.getNumber());
				address.setNeighborhood(a.getNeighborhood());
				address.setState(a.getState());
				address.setCountry(a.getCountry());
				address.setCustomer(customer);
				return address;
			}).collect(Collectors.toList()));
		}
		return customer;
	}
}

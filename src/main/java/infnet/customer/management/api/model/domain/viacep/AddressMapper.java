package infnet.customer.management.api.model.domain.viacep;

import infnet.customer.management.api.model.domain.Address;

public class AddressMapper {

	public static Address fromViaCep(ViaCepResponse viaCep) {
		Address address = new Address();
		address.setCep(viaCep.getCep());
		address.setStreet(viaCep.getLogradouro());
		address.setNeighborhood(viaCep.getBairro());
		address.setState(viaCep.getUf());
		address.setCountry(viaCep.getLocalidade());
		return address;
	}

}

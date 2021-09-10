package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElseGet(Address::new);
    }

    public Result addAddress(Address address) {
        Address addAddress = new Address();
        addAddress.setStreet(address.getStreet());
        addAddress.setHomeNumber(address.getHomeNumber());
        addressRepository.save(addAddress);
        return new Result("Address saved", true);
    }

    public Result deleteAddress(Integer id) {
       try {
           addressRepository.deleteById(id);
           return new Result("Address deleted", true);
       }catch (Exception e) {
           return new Result("Error!!!", false);
       }
    }

    public Result editAddress(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new Result("Address not found", false);
        Address editAddress = optionalAddress.get();
        editAddress.setStreet(address.getStreet());
        editAddress.setHomeNumber(address.getHomeNumber());
        addressRepository.save(editAddress);
        return new Result("Address edited", true);
    }
}

package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAddress() {
        return addressService.getAddress();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Result addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable Integer id){
        return addressService.deleteAddress(id);
    }

    @PutMapping("/{id}")
    public Result editAddress(@PathVariable Integer id, @RequestBody Address address){
        return addressService.editAddress(id, address);
    }
}

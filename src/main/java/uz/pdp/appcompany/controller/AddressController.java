package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Address>> getAddress() {
        List<Address> addresses = addressService.getAddress();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        Address addressById = addressService.getAddressById(id);
        return ResponseEntity.ok(addressById);
    }

    @PostMapping
    public ResponseEntity<Result> addAddress(@RequestBody Address address) {
        Result addAddress = addressService.addAddress(address);
        return ResponseEntity.status(addAddress.isSuccess() ? 201 : 409).body(addAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAddress(@PathVariable Integer id) {
        Result result = addressService.deleteAddress(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editAddress(@PathVariable Integer id, @RequestBody Address address) {
        Result editAddress = addressService.editAddress(id, address);
        return ResponseEntity.status(editAddress.isSuccess() ? 202 : 409).body(editAddress);
    }
}

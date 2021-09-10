package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        List<Company> companyList = companyService.getCompany();
        return ResponseEntity.ok(companyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Integer id) {
        Company companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PostMapping
    public ResponseEntity<Result> addCompany(@RequestBody CompanyDto companyDto) {
        Result result = companyService.addCompany(companyDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Integer id) {
        Result deleteCompany = companyService.deleteCompany(id);
        return ResponseEntity.status(deleteCompany.isSuccess() ? 202 : 409).body(deleteCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        Result editCompany = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(editCompany.isSuccess() ? 202 : 409).body(editCompany);
    }
}

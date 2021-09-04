package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Company> getCompany() {
        return companyService.getCompany();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Result addCompany(@RequestBody CompanyDto companyDto){
        return companyService.addCompany(companyDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCompany(@PathVariable Integer id){
        return companyService.deleteCompany(id);
    }

    @PutMapping("/{id}")
    public Result editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        return companyService.editCompany(id, companyDto);
    }
}

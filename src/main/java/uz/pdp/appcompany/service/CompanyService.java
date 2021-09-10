package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElseGet(Company::new);
    }

    public Result addCompany(CompanyDto companyDto) {
        Company addCompany = new Company();
        addCompany.setCorpName(companyDto.getCorpName());
        addCompany.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Company not found", false);
        addCompany.setAddress(optionalAddress.get());

        companyRepository.save(addCompany);
        return new Result("Company saved", true);
    }

    public Result deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new Result("Company deleted", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }
    }

    public Result editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Company not found", false);
        Company editCompany = optionalCompany.get();
        editCompany.setCorpName(companyDto.getCorpName());
        editCompany.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new Result("Address not found", false);
        editCompany.setAddress(optionalAddress.get());

        companyRepository.save(editCompany);
        return new Result("Company saved", true);
    }
}

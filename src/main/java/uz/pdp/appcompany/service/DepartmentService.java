package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.DepartmentDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.CompanyRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElseGet(Department::new);
    }

    public Result addDepartment(DepartmentDto departmentDto) {
        Department addDepartment = new Department();
        addDepartment.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Company not found", false);
        addDepartment.setCompany(optionalCompany.get());

        departmentRepository.save(addDepartment);
        return new Result("Department saved", true);
    }

    public Result deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new Result("Department deleted", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }
    }

    public Result editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Department not found", false);
        Department editDepartment = optionalDepartment.get();
        editDepartment.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("Company not found", false);
        editDepartment.setCompany(optionalCompany.get());

        departmentRepository.save(editDepartment);
        return new Result("Department edited", true);
    }
}

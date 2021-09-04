package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.DepartmentDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartment(){
        return departmentService.getDepartment();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Integer id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Result addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.addDepartment(departmentDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }

    @PutMapping("/{id}")
    public Result editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        return departmentService.editDepartment(id, departmentDto);
    }
}

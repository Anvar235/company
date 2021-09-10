package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Department>> getDepartment() {

        List<Department> departmentList = departmentService.getDepartment();
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentById);
    }

    @PostMapping
    public ResponseEntity<Result> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Result addDepartment = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(addDepartment.isSuccess() ? 201 : 409).body(addDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Integer id) {
        Result deleteDepartment = departmentService.deleteDepartment(id);
        return ResponseEntity.status(deleteDepartment.isSuccess() ? 202 : 409).body(deleteDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}

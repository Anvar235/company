package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompany.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

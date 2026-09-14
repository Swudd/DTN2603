package backend.repository;

import entity.Department;

import java.util.List;

public interface IQLDepartmentRepository {
    List<Department> GetAllDepartments();
}

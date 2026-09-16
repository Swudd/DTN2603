package backend.service;

import entity.Department;

import java.util.List;

public interface IQLDepartmentService {
    List<Department> getAllDepartments();
    boolean CheckExists(int departmentId);
}

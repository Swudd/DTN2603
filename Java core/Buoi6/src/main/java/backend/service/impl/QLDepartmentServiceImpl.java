package backend.service.impl;

import backend.repository.IQLDepartmentRepository;
import backend.repository.impl.QLDepartmentRepositoryImpl;
import backend.service.IQLDepartmentService;
import entity.Department;

import java.util.List;

public class QLDepartmentServiceImpl implements IQLDepartmentService {
    private IQLDepartmentRepository qlDepartment;
    public QLDepartmentServiceImpl() {
        qlDepartment = new QLDepartmentRepositoryImpl();
    }

    @Override
    public List<Department> getAllDepartments() {
        return qlDepartment.GetAllDepartments();
    }
}

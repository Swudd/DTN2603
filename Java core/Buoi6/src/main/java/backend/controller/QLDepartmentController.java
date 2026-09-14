package backend.controller;

import backend.service.IQLDepartmentService;
import backend.service.impl.QLDepartmentServiceImpl;
import entity.Department;

import java.util.List;

public class QLDepartmentController {
    IQLDepartmentService service;
    public QLDepartmentController(){
        service = new QLDepartmentServiceImpl();
    }

    public List<Department> showAllDepartments(){
        return service.getAllDepartments();
    }

    public static void main(String[] args) {
        QLDepartmentController controller = new QLDepartmentController();
        System.out.println(controller.showAllDepartments().toString());
    }
}

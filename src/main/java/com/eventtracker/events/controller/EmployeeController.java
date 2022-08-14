package com.eventtracker.events.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventtracker.events.Repository.EmployeeRepo;
import com.eventtracker.events.Service.EmployeeService;
import com.eventtracker.events.model.Employee;

@Controller
public class EmployeeController {
   
    private final EmployeeService service;
    private final EmployeeRepo repo;


    public EmployeeController(EmployeeService service, EmployeeRepo repo) {
        this.service = service;
        this.repo = repo;
    } 

    /*
     *   CRUD METHODS 
     */

     @GetMapping("/employee")
     public String getEmployees(Model model) {
        List<Employee> employees = service.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "employees";
     }

     @PostMapping("/create-emp")
     public String createEmployee(RedirectAttributes redirects, @ModelAttribute Employee employee) {
        service.createEmployer(employee);
        return "home"; 
     }

    @GetMapping("/emp/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       Employee employee = service.getEmployee(id);
       model.addAttribute("employee", employee);
       return null;
    }



     @PostMapping("/emp-update/{id}")
     public String updateEmployee(@PathVariable("id") Integer id, @Valid Employee employee, BindingResult result, Model model) {
        if(result.hasErrors()) {
            employee.setId(id);
            return null;
        }
        return null;
     }

     @GetMapping("/emp-del/{id}")
     public String deleteEmployee(@PathVariable("id") Integer id, Model model) {
        Employee employee = service.getEmployee(id);
        repo.delete(employee);
        return null;
     }

    
}

package com.eventtracker.events.Service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eventtracker.events.Repository.EmployeeRepo;
import com.eventtracker.events.model.Employee;

@Service
public class EmployeeService {

    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    // get employee 
    public Employee getEmployee(Integer id) {
        try {
            return repo.findByIdAndActive(id, true).orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    // create an employee 
    public Employee createEmployer(Employee employee) {
        return repo.save(employee);
    }

    // list of employees 
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    // update an employee 
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee fromSQL = getEmployee(id);
        fromSQL.setFirstName(employee.getEmail());
        fromSQL.setLastName(employee.getLastName());
        fromSQL.setEmail(employee.getEmail());
        fromSQL.setEmployeeNumber(employee.getEmployeeNumber());
        fromSQL.setUpdatedAt(employee.getUpdatedAt());
        return repo.save(fromSQL);
    }

    // remove the employee 
    public boolean removeEmployee(Integer id) {
        repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        if (exists == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return !exists;
        }
    }

    
    
}

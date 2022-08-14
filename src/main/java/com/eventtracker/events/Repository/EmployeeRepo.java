package com.eventtracker.events.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventtracker.events.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository <Employee, Integer> {
    Optional<Employee> findByIdAndActive(Integer id, boolean active);
}

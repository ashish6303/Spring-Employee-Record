package com.example.EmoloyeeRecord.services;

import com.example.EmoloyeeRecord.model.Employee;
import com.example.EmoloyeeRecord.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

    @Autowired
    EmployeeRepository employeeRepository;

//    Code to get all the List of Employees
    public ResponseEntity<?> addEmployee(Employee employee)
    {
        Employee emp = employeeRepository.save(employee);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }

//  To get Employees by Id.
    public ResponseEntity<?> getElementById(Long id)
    {
        Optional<Employee> EmployeeById = employeeRepository.findById(id);
        if(EmployeeById.isPresent())
        {
            return ResponseEntity.ok(EmployeeById.get());
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }

// This is for getting all the details of employees.
    public List<Employee> getAllEmployeeDetails()
    {
        List<Employee> emp = new ArrayList<>();
        employeeRepository.findAll().forEach(emp::add);
        return emp;
    }

// This is for delete the Employee Records.
    public ResponseEntity<?> DeleteById(Long id)
    {
       Optional<Employee> deletedEmp  = employeeRepository.findById(id);
       if(deletedEmp.isPresent())
       {
           employeeRepository.deleteById(id);
           return new ResponseEntity<>("Record is deleted Successfully",HttpStatus.FOUND);
       }else
       {
           return new ResponseEntity<>("No Record Found",HttpStatus.NOT_FOUND);
       }
    }

//    This is going to be used for update the user details.
    public ResponseEntity<?> updatebyId(Employee employee, long empid)
    {
        Optional<Employee> emp = employeeRepository.findById(empid);
        if (emp.isPresent()) {
            Employee empExist = emp.get();
            empExist.setEmp_name(employee.getEmp_name());
            empExist.setEmp_age(employee.getEmp_age());
            empExist.setEmp_salary(employee.getEmp_salary());
            empExist.setEmp_city(employee.getEmp_city());

            return ResponseEntity.ok(employeeRepository.save(empExist));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.EmoloyeeRecord.controller;

import com.example.EmoloyeeRecord.model.Employee;
import com.example.EmoloyeeRecord.repository.EmployeeRepository;
import com.example.EmoloyeeRecord.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

//    @Autowired
//    public EmployeeController(EmployeeServices employeeServices) {
//        this.employeeServices = employeeServices;
//    }

//    @PostMapping("/employees")
//    public ResponseEntity<?> createNewEmployee(@RequestBody Employee employee)
//    {
//        Employee createdNewEmployee = EmployeeServices.create
//        return ResponseEntity.ok( "Data has been Saved Successfully");
//    }

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity<?> createNewEmloyee(@RequestBody Employee employee)
    {
//        employeeRepository.save(employee);
        employeeServices.addEmployee(employee);
        return ResponseEntity.ok( "New Employeee record has been Created Successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
         List<Employee> employeeDetails = employeeServices.getAllEmployeeDetails();
         return new ResponseEntity<>(employeeDetails,HttpStatus.OK);
    }

    @GetMapping("/employees/{empid}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable Long empid)
    {
         ResponseEntity<?> e  = employeeServices.getElementById(empid);
         return new ResponseEntity<>(e, HttpStatus.OK);
    }

@PutMapping("/employees/{empid}")
public ResponseEntity<?> updateEmployeeById(@PathVariable Long empid, @RequestBody Employee employee) {
    ResponseEntity<?> e = employeeServices.updatebyId(employee,empid);
    return new ResponseEntity<>(e.getBody(),HttpStatus.OK);
}

    @DeleteMapping("/deleteBy/{empid}")
    public ResponseEntity<?> deleteById(@PathVariable Long empid)
    {
        ResponseEntity<?> e = employeeServices.DeleteById(empid);
        return new ResponseEntity<>(e.getBody(), e.getStatusCode());
    }

}

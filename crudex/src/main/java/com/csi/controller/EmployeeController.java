package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{Email}/{Pwd}")
    public ResponseEntity<Boolean> signIn(@RequestParam String Email, @RequestParam String Pwd) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(Email, Pwd));
    }

    @GetMapping("/findbyid")
    public ResponseEntity<Optional<Employee>> findById(@RequestParam int empId) {
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<Employee>> findByName(@RequestParam String empName) {
        return ResponseEntity.ok(employeeServiceImpl.findByName(empName));
    }

    @GetMapping("/findbyuid")
    public ResponseEntity<Employee> findByUID(@RequestParam long empUID) {
        return ResponseEntity.ok(employeeServiceImpl.findByUID(empUID));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(e -> e.getEmpName())).toList());
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(e -> e.getEmpSalary())).toList());
    }

    @GetMapping("/filterbysalary")
    public ResponseEntity<List<Employee>> filterBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(e -> e.getEmpSalary() > 50000.00).toList());
    }

    @PutMapping("/updatedata")
    public ResponseEntity<Employee> updateData(@RequestParam int empId, @Valid @RequestBody Employee employee) {
        Employee employee1 = employeeServiceImpl.findById(empId).orElseThrow(() -> new RecordNotFoundException("Id does not exist"));

        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpUID(employee.getEmpUID());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpPanCard(employee.getEmpPanCard());

        return new ResponseEntity<>(employeeServiceImpl.updateData(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity<String> deleteById(@RequestParam int empId) {
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}

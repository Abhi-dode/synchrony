package com.dode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dode.entity.Employee;
import com.dode.service.IEmployeeMgmtService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	IEmployeeMgmtService employeeService;

	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getEntity(@PathVariable Integer id) {
		logger.info("Get Employee by ID, ID: {}",id);
		return new ResponseEntity<Employee>(employeeService.getEntity(id), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Employee> createEntity(@RequestBody Employee entity) {
		logger.info("Add new Employee, Employee: {}",entity.toString());
		return new ResponseEntity<Employee>(employeeService.saveEntity(entity), HttpStatus.CREATED);
	}
	
	@GetMapping("/getStatus")
	public ResponseEntity<String> statusCheck() {
		return new ResponseEntity<String>("Active", HttpStatus.OK);
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<Employee>> findByInitialName(@PathVariable String name) {
		logger.info("Get Employee by name, Name: {}",name);
		return new ResponseEntity<List<Employee>> (employeeService.findEmployeeStartWithName(name), HttpStatus.OK);
	}
	
	@GetMapping("/findByDept/{dept}")
	public ResponseEntity<List<Employee>> findByDeptName(@PathVariable String dept){
		logger.info("Get Employee by Department, Department: {}",dept);
		return new ResponseEntity<List<Employee>> (employeeService.findEmployeeByDept(dept), HttpStatus.OK);
	}
	
	@GetMapping("/salaryIncrement/{eno}/{hikePercentage}")
	public ResponseEntity<Employee> incrementInSalary(@PathVariable Long eno, @PathVariable Float  hikePercentage) {
		return new ResponseEntity<Employee>(employeeService.salaryIncremat(eno,hikePercentage), HttpStatus.OK);
	}
	
	

}

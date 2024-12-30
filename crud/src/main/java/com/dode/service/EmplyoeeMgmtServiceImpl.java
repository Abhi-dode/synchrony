package com.dode.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dode.controller.EmployeeController;
import com.dode.entity.Employee;
import com.dode.repository.EmployeeRepository;

@Service
public class EmplyoeeMgmtServiceImpl implements IEmployeeMgmtService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmplyoeeMgmtServiceImpl.class);
	
	@Autowired EmployeeRepository employeeRepo;

	@Override
	@CacheEvict(value = {"employeeNameCache", "employeeDeptCache"}, allEntries = true)
	public Employee saveEntity(Employee employee) {
		logger.info("method: saveEntity, Employee: {}",employee.toString());
		return employeeRepo.save(employee);
	}

	@Override
	@Cacheable(value = "employeeId", key = "#id")
	public Employee getEntity(Integer id) {
		logger.info("method getEntity, ID: {}",id);
		Optional<Employee> empOptional=employeeRepo.findById(id);
		if(empOptional.isPresent()) {
			return empOptional.get();
		}else {
			return null;
		}
	}

	@Override
	@Cacheable(value = "employeeNameCache", key = "#initialName")
	public List<Employee> findEmployeeStartWithName(String initialName) {
		logger.info("method : findEmployeeStartWithName, Employee name: {}",initialName);
		return employeeRepo.findByEnameStartingWith(initialName);
	}

	@Override
	@Cacheable(value = "employeeDeptCache", key = "#Dept")
	public List<Employee> findEmployeeByDept(String dept) {
		logger.info("method : findEmployeeByDept, Department: {}",dept);
		return employeeRepo.findByEdepartment(dept);
	}

	//Salary increment calculation we are doing in repo class
	@Override
	public Employee salaryIncremat(Long eno, Float percentage) {
		int update=employeeRepo.salaryIncrement(eno, percentage);
		if(update>=1) {
			return employeeRepo.findByEno(eno);
		}else {
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		//First find employee
		Employee dbEmployee=employeeRepo.getReferenceById(employee.getId());
		dbEmployee.setEname(employee.getEname()); dbEmployee.setEno(employee.getEno()); dbEmployee.setEsalary(employee.getEsalary()); dbEmployee.setEdepartment(employee.getEdepartment());
		return employeeRepo.save(dbEmployee);
		
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeRepo.deleteById(id);
	}
	
	

}

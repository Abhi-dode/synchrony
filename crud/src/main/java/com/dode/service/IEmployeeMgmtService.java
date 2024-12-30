package com.dode.service;

import java.util.List;

import com.dode.entity.Employee;

public interface IEmployeeMgmtService {
	public Employee saveEntity(Employee employee);
	public Employee getEntity(Integer id);
	public List<Employee> findEmployeeStartWithName(String initialName);
	public List<Employee> findEmployeeByDept(String dept);
	public Employee salaryIncremat(Long eno, Float percentage);
	public Employee updateEmployee(Employee employee);
	public void deleteEmployee(Integer id);
}

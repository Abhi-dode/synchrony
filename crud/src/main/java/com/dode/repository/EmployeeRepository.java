package com.dode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dode.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findByEnameStartingWith(String initialName);
	public List<Employee> findByEdepartment(String dept);
	public Employee findByEno(Long eno);
	
	@Modifying
	@Transactional
	@Query("update Employee e set e.esalary = e.esalary + (e.esalary * :percentage / 100) where e.eno = :eno")
	public int salaryIncrement(Long eno, Float percentage);
		
}

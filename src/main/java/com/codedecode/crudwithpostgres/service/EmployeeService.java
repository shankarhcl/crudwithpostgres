package com.codedecode.crudwithpostgres.service;

import java.util.List;

import com.codedecode.crudwithpostgres.entity.Employee;

public interface EmployeeService {

	Employee saveEmpDtls(Employee employee);

	List<Employee> getEmpDtls();

	Employee getEmpDtlsById(Long id);

	void deleteEmpDtls();

	void deleteEmpDtlsById(Long id);

	Employee updateEmpDtls(Employee employee);

	
}

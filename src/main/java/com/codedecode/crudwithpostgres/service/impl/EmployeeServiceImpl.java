package com.codedecode.crudwithpostgres.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.crudwithpostgres.constants.Constants;
import com.codedecode.crudwithpostgres.constants.ErrorCode;
import com.codedecode.crudwithpostgres.entity.Employee;
import com.codedecode.crudwithpostgres.exception.BusinessException;
import com.codedecode.crudwithpostgres.repository.EmployeeRepository;
import com.codedecode.crudwithpostgres.service.EmployeeService;
import com.codedecode.crudwithpostgres.validation.Validation;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private Validation validation;
	
	@Override
	public Employee saveEmpDtls(Employee employee) {
		//we have not used throws here as all these ex are unchecked so not required
		//validation should never came in try otherwise last throw will execute at last
		    validation.checkEmpname(employee);
			try {
			Employee empDtls =	employeeRepository.save(employee);
			return empDtls;
			}catch (IllegalArgumentException e) {
					throw new BusinessException(ErrorCode.BE_002, "Employee object is null", e.getMessage());
			}catch (Exception e) {
				throw new BusinessException(ErrorCode.BE_003, Constants.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}

	@Override
	public List<Employee> getEmpDtls() {
		List<Employee> empList=null;
		try {
		empList=employeeRepository.findAll().stream().collect(Collectors.toList());
		
	    //Map<Long, List<Employee>> map=empList.stream().collect(Collectors.groupingBy(e->e.getId()));
	    //Map<Long, Long> map=empList.stream().collect(Collectors.groupingBy(e->e.getId(),Collectors.counting()));
	   // Map<Long, List<Employee>> map=empList.stream().collect(Collectors.groupingBy(e->e.getId(),Collectors.toList()));
		//  Map<Long, Set<Employee>> map=empList.stream().collect(Collectors.groupingBy(e->e.getId(),Collectors.toSet()));
		//Map<Employee, Set<Employee>> map=empList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.toSet()));
		Map<Long, Set<Employee>> map=empList.stream().collect(Collectors.groupingBy(e->e.getId(), TreeMap::new, Collectors.toSet()));

		
		System.out.println(map);
		//Set<String> uniqueName = new HashSet<>();
		//empList.stream().map(e->!uniqueName.add(e.getName())).collect(Collectors.toList());
		
		
        //Map<String, Long> map	=	empList.stream().map(e->e.getName()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	    //Set<String> set =	map.entrySet().stream().filter(entry-> entry.getValue()>1).map(entry->entry.getKey()).collect(Collectors.toSet());
		
		//empList.stream().map(e->e.getName()).filter(name-> Collections.frequency(empList, name)>1).collect(Collectors.toSet());
		
		}catch (Exception e) {
			throw new BusinessException(ErrorCode.BE_004, Constants.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		if(empList.isEmpty()) 
			throw new BusinessException(ErrorCode.BE_005, "No Record Found", "No data found");
		return empList;
	}

	@Override
	public Employee getEmpDtlsById(Long id) {
		
		  Employee employee = employeeRepository.findById(id).get(); 
		  return employee;
		 
		/*
		 * Employee emp = new Employee(0L,"Not Found","Not Found"); Optional<Employee>
		 * op = employeeRepository.findById(id); if(!op.isEmpty()) { // String name =
		 * Optional.ofNullable(op.get().getName()).orElse("name is null");--now no need
		 * og ifPresent or isPresent // String name =
		 * Optional.ofNullable(op.get().getName()).orElseGet(()->"name is null");--now
		 * no need og ifPresent or isPresent // String name =
		 * Optional.ofNullable(op.get().getName()).orElseThrow(()->new
		 * IllegalArgumentException("name is null"));--now no need og ifPresent or
		 * isPresent
		 * 
		 * Optional<String> name = Optional.ofNullable(op.get().getName());
		 * //name.ifPresentOrElse(System.out::println, ()->
		 * System.out.println("name is null"));--now no need of isPresent or orElse
		 * if(name.isPresent()) name.get().toUpperCase(); else
		 * System.out.println("name is null"); } else {
		 * System.out.println("given id is not present in db"); return emp;
		 * 
		 * } return op.get();
		 */
	}

	@Override
	public void deleteEmpDtls() {
		 employeeRepository.deleteAll();
	}

	@Override
	public void deleteEmpDtlsById(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updateEmpDtls(Employee employee) {
		Optional<Employee> opt = employeeRepository.findById(employee.getId());
		Employee emp=new Employee();
		if(opt.isPresent())
			emp=employeeRepository.save(employee);
		return emp;
	}
}

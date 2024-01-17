package com.codedecode.crudwithpostgres.validation;

import org.springframework.stereotype.Component;

import com.codedecode.crudwithpostgres.constants.ErrorCode;
import com.codedecode.crudwithpostgres.entity.Employee;
import com.codedecode.crudwithpostgres.exception.BusinessException;

@Component
public class Validation {
	
	public void checkEmpname(Employee employee) {
	
	if(null==employee.getName() || employee.getName().isEmpty())
		throw new BusinessException(ErrorCode.BE_001,"Name should not be empty or null","Error in name");
	}
}
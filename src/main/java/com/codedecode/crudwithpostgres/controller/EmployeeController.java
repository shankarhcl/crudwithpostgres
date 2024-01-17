package com.codedecode.crudwithpostgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.crudwithpostgres.constants.Constants;
import com.codedecode.crudwithpostgres.constants.ErrorCode;
import com.codedecode.crudwithpostgres.entity.Employee;
import com.codedecode.crudwithpostgres.exception.BusinessException;
import com.codedecode.crudwithpostgres.exception.ControllerException;
import com.codedecode.crudwithpostgres.model.Meta;
import com.codedecode.crudwithpostgres.model.ResponseDto;
import com.codedecode.crudwithpostgres.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${api.version}")
@Slf4j
public class EmployeeController {
	
	@Autowired 
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/Employee", method = RequestMethod.POST)
	public ResponseDto<?> saveEmpDtls(@RequestBody Employee employee){
		Meta meta = new Meta();
		try {
			Employee empDtls = employeeService.saveEmpDtls(employee);
			meta.setCode(Constants.HTTP_STATUS_201);
			meta.setStatus(Constants.CREATED);
			meta.setHttpCode(Constants.HTTP_STATUS_201);
			meta.setDesc(Constants.SUCCESS);
			return new ResponseDto<Employee>(meta, empDtls);
		}catch (BusinessException e) {
			meta.setCode(Constants.HTTP_STATUS_400);
			meta.setStatus(Constants.NOT_CREATED);
			meta.setHttpCode(Constants.HTTP_STATUS_400);
			meta.setDesc(Constants.FAILURE);
				ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMsg(),e.getErrorDesc());
				return new ResponseDto<ControllerException>(meta,ce);
		}catch (Exception e) {
			meta.setCode(Constants.HTTP_STATUS_400);
			meta.setStatus(Constants.NOT_CREATED);
			meta.setHttpCode(Constants.HTTP_STATUS_400);
			meta.setDesc(Constants.FAILURE);
			ControllerException ce = new ControllerException(ErrorCode.CE_001,Constants.INTERNAL_SERVER_ERROR,Constants.HTTP_STATUS_500);
			return new ResponseDto<ControllerException>(meta,ce);
	}
}

	@RequestMapping(value = "/Employees", method = RequestMethod.GET)
	public ResponseEntity<?> getEmpDtls(){
		try {
		List<Employee> empDtls = employeeService.getEmpDtls();
		return new ResponseEntity<List<Employee>>(empDtls, HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMsg(),e.getErrorDesc());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			ControllerException ce = new ControllerException(ErrorCode.CE_002,Constants.INTERNAL_SERVER_ERROR,Constants.HTTP_STATUS_500);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
}
	//done by Global exception handler with predefined classes
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.GET)
	public ResponseDto<?> getEmpDtlsById(@PathVariable("id") Long id){
		Employee emp = employeeService.getEmpDtlsById(id);
		Meta meta = new Meta();
		meta.setCode(Constants.HTTP_STATUS_200);
		meta.setStatus(Constants.Found);
		meta.setHttpCode(Constants.HTTP_STATUS_200);
		meta.setDesc(Constants.SUCCESS);
		return new ResponseDto<Employee>(meta, emp);
		
	}
	
	@RequestMapping(value = "/Employees", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmpDtls(){
		employeeService.deleteEmpDtls();
		return new ResponseEntity<>("All Records Deleted",HttpStatus.OK);
	}
	
	//done by global exception handler with predefined classes
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmpDtlsById(@PathVariable("id") Long id){
		employeeService.deleteEmpDtlsById(id);
		return new ResponseEntity<String>("Record Deleted",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Employee", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmpDtls(@RequestBody Employee employee){
		log.info("employee object in request: {}"+employee);
		Employee updatedEmpDtls = employeeService.updateEmpDtls(employee);
		return new ResponseEntity<>(updatedEmpDtls, HttpStatus.ACCEPTED);
	}
}

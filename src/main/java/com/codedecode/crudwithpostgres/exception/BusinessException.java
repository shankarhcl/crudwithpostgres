package com.codedecode.crudwithpostgres.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMsg;
	private String errorDesc;


}

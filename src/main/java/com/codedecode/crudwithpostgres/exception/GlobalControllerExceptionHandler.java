package com.codedecode.crudwithpostgres.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codedecode.crudwithpostgres.constants.Constants;
import com.codedecode.crudwithpostgres.model.Meta;
import com.codedecode.crudwithpostgres.model.ResponseDto;


@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	//if not overidding ResponseEntityExceptionHandler then also can do exception handling but can't overirde methods of ResponseEntityExceptionHandler
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseDto<?> handleNoSuchElementException(NoSuchElementException  ex){
		Meta meta = new Meta();
		meta.setCode(Constants.HTTP_STATUS_404);
		meta.setStatus(Constants.No_DATA_FOUND);
		meta.setHttpCode(Constants.HTTP_STATUS_404);
		meta.setDesc(Constants.FAILURE);
		return new ResponseDto<NoSuchElementException>(meta,ex);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException  ex){
		return new ResponseEntity<String>("No data present for given id",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException  ex){
		return new ResponseEntity<String>("Some Issue occurred",HttpStatus.INTERNAL_SERVER_ERROR);
	}


	//can't override if not extending ResponseEntityExceptionHandler
	//if any of the api will throw Method not allowed then this handler will inv
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		//return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
		return new ResponseEntity<Object>("Kindly change HTTP method type",HttpStatus.NOT_FOUND);

	}
}

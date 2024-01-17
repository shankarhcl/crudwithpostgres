package com.codedecode.crudwithpostgres.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Meta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String status;
	private String httpCode;
	private String desc;

}

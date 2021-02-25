package com.example.demo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.constants.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
	@NotEmpty(message=ErrorCode.BLANKFIELD + "-El campo name no puede estar en blanco.")
	@NotBlank(message=ErrorCode.BLANKFIELD + "-El campo name no puede estar en blanco.") 
	@Size(max = 100, message=ErrorCode.LENGTHOUTOFRANGE+"-Se ha excedido el número de caracteres permitidos en el campo name.")	
	@Pattern(message=ErrorCode.INCORRECTFIELDFORMAT +"-El campo name no tiene el formato adecuado.", regexp = "[A-Za-z\\u00F1\\u00D1 äëïöüàâáéíóúçÄËÏÖÜÇ']+(\\\\s|\\\\-[A-Za-z\\\\u00F1\\\\u00D1 äëïöüàâáéíóúçÄËÏÖÜÇ']+)*")
	private String name;
	
	@NotEmpty(message=ErrorCode.BLANKFIELD + "-El campo lastname no puede estar en blanco.")
	@NotBlank(message=ErrorCode.BLANKFIELD + "-El campo lastname no puede estar en blanco.") 
	@Size(max = 100, message=ErrorCode.LENGTHOUTOFRANGE+"-Se ha excedido el número de caracteres permitidos en el campo lastname.")	
	@Pattern(message=ErrorCode.INCORRECTFIELDFORMAT +"-El campo lastname no tiene el formato adecuado.", regexp = "[A-Za-z\\u00F1\\u00D1 äëïöüàâáéíóúçÄËÏÖÜÇ']+(\\\\s|\\\\-[A-Za-z\\\\u00F1\\\\u00D1 äëïöüàâáéíóúçÄËÏÖÜÇ']+)*")
	private String lastname;
	
	@NotEmpty(message=ErrorCode.BLANKFIELD + "-El campo birthdate no puede estar en blanco.")
	@NotBlank(message=ErrorCode.BLANKFIELD + "-El campo birthdate no puede estar en blanco.") 
	private String birthdate;
}

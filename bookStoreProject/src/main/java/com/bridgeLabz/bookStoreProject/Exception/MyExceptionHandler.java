package com.bridgeLabz.bookStoreProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgeLabz.bookStoreProject.DTO.ResponseDTO;

@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(CustomException.class)
	ResponseEntity<ResponseDTO> handleNonExistingId(CustomException customException){
		ResponseDTO response = new ResponseDTO("Exception while passing rest request", customException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
	}

}

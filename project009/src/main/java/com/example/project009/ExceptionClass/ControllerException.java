package com.example.project009.ExceptionClass;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ControllerException {

	private String errorCode;
	private String errorMessage;
}

package com.recipe.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExceptionResponse {

	private String message;
	private String details;
}
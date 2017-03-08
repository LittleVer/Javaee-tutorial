package com.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualValidator implements ConstraintValidator<Equal, String[]> {

	@Override
	public void initialize(Equal constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String[] arg0, ConstraintValidatorContext arg1) {
		if(arg0.length!=2)
			return false;
		if(arg0[0]==null || arg0[1]==null)
			return false;
		if(!arg0[0].trim().equals(arg0[1].trim()))
			return false;
		return true;
	}

}

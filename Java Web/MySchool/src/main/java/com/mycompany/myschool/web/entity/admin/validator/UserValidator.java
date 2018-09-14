package com.mycompany.myschool.web.entity.admin.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.myschool.web.entity.base.UserEntity;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> target) {
		return UserEntity.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserEntity userEntity = (UserEntity)target;
		 
		if (userEntity.getUser_id() == null || userEntity.getUser_id().length() == 0) {
			errors.rejectValue("user_id", "error.required.user_id");
		} 
		
		if (userEntity.getPwd() == null || userEntity.getPwd().length() == 0) {
			errors.rejectValue("pwd", "error.required.pwd");
		} 
		
		if (userEntity.getEmail() == null || userEntity.getEmail().length() == 0) {
			errors.rejectValue("email", "error.required.email");
		} else {
			Pattern pattern = Pattern.compile("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$");
			Matcher match = pattern.matcher(userEntity.getEmail());
			if (!match.find()) {
				errors.rejectValue("email", "error.invalid.email");
			}
		}
	}

}

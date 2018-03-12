package com.kids.crm.validator;

import com.kids.crm.model.Signup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Signup.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"firstName", "","FirstName can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"lastName", "","LastName can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"dateOfBirth", "","DateOfBirth can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"phoneNo", "","PhoneNo can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"address", "","Address can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"email", "","Email can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"password", "","Password can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword", "","ConfirmPassword can't be empty");

        Signup signup = (Signup) target;
        if(StringUtils.isNoneBlank(signup.getPassword()) && StringUtils.isNoneBlank(signup.getConfirmPassword()) && !signup.getPassword().equals(signup.getConfirmPassword())){
            errors.rejectValue("password", "","Password and ConfirmPassword must be same");
        }

        if(signup.getEnrollingIds() == null || signup.getEnrollingIds().length == 0){
            errors.rejectValue("enrollingIds", "","Please select a enroll subject");
        }
    }
}

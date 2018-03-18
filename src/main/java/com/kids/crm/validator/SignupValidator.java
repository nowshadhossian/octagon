package com.kids.crm.validator;

import com.kids.crm.controller.RegistrationController;
import com.kids.crm.model.Signup;
import com.kids.crm.model.User;
import com.kids.crm.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;

@Component
public class SignupValidator implements Validator {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Signup.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "", "FirstName can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "", "LastName can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "dateOfBirth", "", "DateOfBirth can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "phoneNo", "", "PhoneNo can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "address", "", "Address can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "", "Email can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "", "Password can't be empty");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "", "ConfirmPassword can't be empty");

        Signup signup = (Signup) target;
        if (StringUtils.isNoneBlank(signup.getPassword()) && StringUtils.isNoneBlank(signup.getConfirmPassword()) && !signup.getPassword().equals(signup.getConfirmPassword())) {
            errors.rejectValue("password", "", "Password and ConfirmPassword must be same");
        }

        if (signup.getEnrollingIds() == null || signup.getEnrollingIds().length == 0) {
            errors.rejectValue("enrollingIds", "", "Please select a enroll subject");
        }

        if (StringUtils.isNotBlank(signup.getDateOfBirth())) {
            try {
                RegistrationController.df.parse(signup.getDateOfBirth());
            } catch (ParseException e) {
                errors.rejectValue("dateOfBirth", "", "Wrong date format");
            }
        }

        if (errors.getErrorCount() == 0 && StringUtils.isNotBlank(signup.getEmail()) && userRepository.findByEmail(signup.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Email Taken");
        }

    }
}

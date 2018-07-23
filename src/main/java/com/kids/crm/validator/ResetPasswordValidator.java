package com.kids.crm.validator;

import com.kids.crm.model.ResetPassword;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ResetPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ResetPassword.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"userId","User not valid");
        ValidationUtils.rejectIfEmpty(errors,"password","Password can't be empty");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","ConfirmPassword can't be empty");
        ResetPassword resetPassword = (ResetPassword) target;
        if (StringUtils.isNoneBlank(resetPassword.getPassword()) && StringUtils.isNoneBlank(resetPassword.getConfirmPassword()) && !resetPassword.getPassword().equals(resetPassword.getConfirmPassword())){
            errors.rejectValue("password","","Password and ConfirmPassword must be same");
        }
    }
}

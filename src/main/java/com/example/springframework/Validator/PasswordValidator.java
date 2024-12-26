package com.example.springframework.Validator;

import com.example.springframework.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // password at least 8 characters with one upper case and one special character
        String regex = "^(?=(?=.*[A-Z]).*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        return password.matches(regex);
    }
}

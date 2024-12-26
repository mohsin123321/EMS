package com.example.springframework.annotation;

import com.example.springframework.Validator.PasswordValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    /**
     * error message.s
     * @return the error message.
     */
    String message() default "Invalid Password";

    /**
     * define under which circumstances this validation is to be triggered.
     * @return the groups.
     */
    Class<?>[] groups() default {};

    /**
     * define a payload to be passed with this validation.
     * @return the payload.
     */
    Class<?>[] payload() default {};
}

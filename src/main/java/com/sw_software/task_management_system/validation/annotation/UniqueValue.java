package com.sw_software.task_management_system.validation.annotation;

import com.sw_software.task_management_system.validation.validator.UniqueValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
public @interface UniqueValue {
  String message() default "Value must be unique";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  Class<?> domainClass();

  String fieldName();
}

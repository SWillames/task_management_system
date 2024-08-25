package com.sw_software.task_management_system.validation.validator;

import com.sw_software.task_management_system.validation.annotation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  @PersistenceContext
  private EntityManager em;

  private String fieldName;
  private Class<?> entityClass;

  @Override
  public void initialize(UniqueValue constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
    this.entityClass = constraintAnnotation.domainClass();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext context) {
    String query = String.format("SELECT 1 FROM %s WHERE %s = :value", entityClass.getSimpleName(), fieldName);
    boolean exists = !em.createQuery(query)
        .setParameter("value", o)
        .getResultList()
        .isEmpty();
    return !exists;
  }
}

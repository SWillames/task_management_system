package com.sw_software.task_management_system.exceptions;

public class AlreadyRegisteredException extends RuntimeException {
  public AlreadyRegisteredException(String message) {
    super(message);
  }
}

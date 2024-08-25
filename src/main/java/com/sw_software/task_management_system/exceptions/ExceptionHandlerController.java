package com.sw_software.task_management_system.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<ErrorMessageDTO> dto = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach((error) -> {
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
      ErrorMessageDTO errorDTO = new ErrorMessageDTO(message, error.getField());
      dto.add(errorDTO);
    });
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyRegisteredException.class)
  public ResponseEntity<Object> handleAlreadyRegisteredException(AlreadyRegisteredException ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.CONFLICT);
    return new ResponseEntity<>(map, HttpStatus.CONFLICT);
  }
}

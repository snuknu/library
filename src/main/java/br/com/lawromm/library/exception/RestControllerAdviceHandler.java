package br.com.lawromm.library.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviceHandler {
  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<DtoOutError> entityNotFoundException(EntityNotFoundException ex) {
    return new ResponseEntity<>(
      new DtoOutError(
        ex.getMessage(),
        messageSource.getMessage(
          "exception.EntityNotFound",
          null,
          LocaleContextHolder.getLocale()
        )
      ),
      HttpStatus.NOT_FOUND
    );
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<DtoOutError> dataIntegrityViolationException(
    DataIntegrityViolationException ex
  ) {
    return new ResponseEntity<>(
      new DtoOutError(
        ex.getMessage(),
        messageSource.getMessage(
          "exception.DataIntegrityViolation",
          null,
          LocaleContextHolder.getLocale()
        )
      ),
      HttpStatus.BAD_REQUEST
    );
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<DtoOutErrorInvalidField>> handle(
    MethodArgumentNotValidException ex
  ) {
    List<FieldError> invalidFields = ex.getBindingResult().getFieldErrors();
    return new ResponseEntity<>(
      invalidFields
        .stream()
        .map(
          erro -> {
            String customError = messageSource.getMessage(
              "exception.MethodArgumentNotValid",
              null,
              LocaleContextHolder.getLocale()
            );

            return new DtoOutErrorInvalidField(
              erro.getField(),
              messageSource.getMessage(erro, LocaleContextHolder.getLocale()),
              customError
            );
          }
        )
        .collect(Collectors.toList()),
      HttpStatus.BAD_REQUEST
    );
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(NoSuchMessageException.class)
  public ResponseEntity<DtoOutError> noSuchMessageException(NoSuchMessageException ex) {
    return new ResponseEntity<>(
      new DtoOutError(
        ex.getMessage(),
        messageSource.getMessage(
          "exception.NoSuchMessage",
          null,
          LocaleContextHolder.getLocale()
        )
      ),
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<DtoOutError> httpMessageNotReadableException(
    HttpMessageNotReadableException ex
  ) {
    return new ResponseEntity<>(
      new DtoOutError(
        ex.getMessage(),
        messageSource.getMessage(
          "exception.HttpMessageNotReadable",
          null,
          LocaleContextHolder.getLocale()
        )
      ),
      HttpStatus.BAD_REQUEST
    );
  }
}

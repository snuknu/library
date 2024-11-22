package br.com.lawromm.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOutErrorInvalidField {
  private String field;
  private String error;
  private String customError;
}

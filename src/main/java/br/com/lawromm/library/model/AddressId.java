package br.com.lawromm.library.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressId implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Integer addressType;
}

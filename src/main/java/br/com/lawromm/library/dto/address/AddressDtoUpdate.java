package br.com.lawromm.library.dto.address;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddressDtoUpdate {
  @NotNull
  private Long id;

  @NotNull
  private Integer addressType;

  private String street;
  private String number;
  private String district;
  private String city;
  private String state;
  private String country;
  private Integer zipCode;
  private String receiverName;
  private String receiverPhone;
  private String comments;
}

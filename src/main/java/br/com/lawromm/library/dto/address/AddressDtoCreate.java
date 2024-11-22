package br.com.lawromm.library.dto.address;

import jakarta.validation.constraints.NotBlank;
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
public class AddressDtoCreate {
  @NotNull
  private Long id;

  @NotNull
  private Integer addressType;

  @NotBlank
  private String street;

  @NotBlank
  private String number;

  @NotBlank
  private String district;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  @NotBlank
  private String country;

  @NotBlank
  private Integer zipCode;

  @NotBlank
  private String receiverName;

  @NotBlank
  private String receiverPhone;

  private String comments;
}

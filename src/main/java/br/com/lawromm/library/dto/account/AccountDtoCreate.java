package br.com.lawromm.library.dto.account;

import br.com.lawromm.library.enumeration.AccountStatus;
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
public class AccountDtoCreate {
  @NotBlank
  private String login;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  @NotBlank
  private String phone;

  @NotBlank
  private String email;

  @NotBlank
  private String document;

  @NotNull
  private AccountStatus accountStatus;

  private AccountDtoUpdate accountIndicated;
}

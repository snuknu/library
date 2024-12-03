package br.com.lawromm.library.dto.account;

import java.util.ArrayList;
import java.util.List;
import br.com.lawromm.library.dto.address.AddressDtoUpdate;
import br.com.lawromm.library.enumeration.AccountStatus;
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
public class AccountDtoUpdate {
  @NotNull
  private Long id;

  private String login;
  private String password;
  private String name;
  private String phone;
  private String email;
  private String document;
  private AccountStatus accountStatus;
  private List<AddressDtoUpdate> addressList = new ArrayList<>();
}

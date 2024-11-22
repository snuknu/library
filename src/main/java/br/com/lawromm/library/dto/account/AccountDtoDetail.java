package br.com.lawromm.library.dto.account;

import br.com.lawromm.library.dto.address.AddressDtoSummary;
import br.com.lawromm.library.enumeration.AccountStatus;
import br.com.lawromm.library.model.Account;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
public class AccountDtoDetail {
  private Long id;
  private String login;
  private String password;
  private String name;
  private String phone;
  private String email;
  private String document;
  private LocalDateTime creationDate;
  private AccountStatus accountStatus;
  private AccountDtoSummary accountIndicated;
  private List<AddressDtoSummary> addressList = new ArrayList<>();

  public AccountDtoDetail(Account entity) {
    this(
      entity.getId(),
      entity.getLogin(),
      entity.getPassword(),
      entity.getName(),
      entity.getPhone(),
      entity.getEmail(),
      entity.getDocument(),
      entity.getCreationDate(),
      entity.getAccountStatus(),
      Objects.nonNull(entity.getAccountIndicated())
        ? new AccountDtoSummary(
          entity.getAccountIndicated().getId(),
          entity.getAccountIndicated().getName(),
          entity.getAccountIndicated().getPhone()
        )
        : null,
      AddressDtoSummary.toList(entity.getAddressList())
    );
  }

  public static List<AccountDtoDetail> toList(List<Account> entityList) {
    return Objects.nonNull(entityList)
      ? entityList.stream().map(AccountDtoDetail::new).collect(Collectors.toList())
      : new ArrayList<AccountDtoDetail>();
  }
}

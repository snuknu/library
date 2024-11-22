package br.com.lawromm.library.dto.account;

import br.com.lawromm.library.model.Account;
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
public class AccountDtoSummary {
  private Long id;
  private String name;
  private String phone;

  public AccountDtoSummary(Account entity) {
    this(entity.getId(), entity.getName(), entity.getPhone());
  }

  public static List<AccountDtoSummary> toList(List<Account> entityList) {
    return Objects.nonNull(entityList)
      ? entityList.stream().map(AccountDtoSummary::new).collect(Collectors.toList())
      : new ArrayList<AccountDtoSummary>();
  }
}

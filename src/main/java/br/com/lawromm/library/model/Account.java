package br.com.lawromm.library.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import br.com.lawromm.library.dto.account.AccountDtoCreate;
import br.com.lawromm.library.dto.account.AccountDtoUpdate;
import br.com.lawromm.library.enumeration.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "account_seq", sequenceName = "account_seq")
  private Long id;

  private String login;
  private String password;
  private String name;
  private String phone;
  private String email;
  private String document;
  private LocalDateTime creationDate;

  @Enumerated(EnumType.ORDINAL)
  private AccountStatus accountStatus;

  @ManyToOne
  @JoinColumn(
    name = "account_indicated_id",
    referencedColumnName = "id",
    insertable = false,
    updatable = false
  )
  private Account accountIndicated;

  @OneToMany(mappedBy = "account")
  private List<Address> addressList = new ArrayList<>();

  public Account(Long id) {
    this.id = id;
  }

  public Account(AccountDtoCreate dto) {
    this(
      null,
      dto.getLogin(),
      dto.getPassword(),
      dto.getName(),
      dto.getPhone(),
      dto.getEmail(),
      dto.getDocument(),
      LocalDateTime.now(),
      dto.getAccountStatus(),
      new Account(dto.getAccountIndicated().getId()),
      null
    );
  }

  public Account(AccountDtoUpdate dto) {
    this.id = dto.getId();
  }

  public Account update(AccountDtoUpdate dto) {
    if (Objects.nonNull(dto.getLogin())) this.login = dto.getLogin();
    if (Objects.nonNull(dto.getPassword())) this.password = dto.getPassword();
    if (Objects.nonNull(dto.getName())) this.name = dto.getName();
    if (Objects.nonNull(dto.getPhone())) this.phone = dto.getPhone();
    if (Objects.nonNull(dto.getEmail())) this.email = dto.getEmail();
    if (Objects.nonNull(dto.getDocument())) this.document = dto.getDocument();
    if (Objects.nonNull(dto.getAccountStatus())) this.accountStatus = dto.getAccountStatus();
    if (Objects.nonNull(dto.getAddressList())) this.addressList.addAll(
        Address.toList(dto.getAddressList())
      );
    return this;
  }

  public static List<Account> toList(List<AccountDtoUpdate> dotList) {
    return Objects.nonNull(dotList)
      ? dotList.stream().map(Account::new).collect(Collectors.toList())
      : new ArrayList<Account>();
  }
}

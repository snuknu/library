package br.com.lawromm.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import br.com.lawromm.library.dto.address.AddressDtoCreate;
import br.com.lawromm.library.dto.address.AddressDtoUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@IdClass(AddressId.class)
public class Address {
  @Id
  private Long id;

  @Id
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

  @ManyToOne
  @JoinColumn(name = "id", insertable = false, updatable = false)
  private Account account;

  public Address(AddressDtoCreate dto) {
    this(
      dto.getId(),
      dto.getAddressType(),
      dto.getStreet(),
      dto.getNumber(),
      dto.getDistrict(),
      dto.getCity(),
      dto.getState(),
      dto.getCountry(),
      dto.getZipCode(),
      dto.getReceiverName(),
      dto.getReceiverPhone(),
      dto.getComments(),
      new Account(dto.getId())
    );
  }

  public Address(AddressDtoUpdate dto) {
    this.id = dto.getId();
    this.addressType = dto.getAddressType();
  }

  public Address update(AddressDtoUpdate dto) {
    if (Objects.nonNull(dto.getStreet())) this.street = dto.getStreet();
    if (Objects.nonNull(dto.getNumber())) this.number = dto.getNumber();
    if (Objects.nonNull(dto.getDistrict())) this.district = dto.getDistrict();
    if (Objects.nonNull(dto.getCity())) this.city = dto.getCity();
    if (Objects.nonNull(dto.getState())) this.state = dto.getState();
    if (Objects.nonNull(dto.getCountry())) this.country = dto.getCountry();
    if (Objects.nonNull(dto.getZipCode())) this.zipCode = dto.getZipCode();
    if (Objects.nonNull(dto.getReceiverName())) this.receiverName = dto.getReceiverName();
    if (Objects.nonNull(dto.getReceiverPhone())) this.receiverPhone =
      dto.getReceiverPhone();
    if (Objects.nonNull(dto.getComments())) this.comments = dto.getComments();
    return this;
  }

  public Address update(List<AddressDtoUpdate> dtoList) {
    dtoList.forEach(dto -> update(dto));
    return this;
  }

  public static List<Address> toList(List<AddressDtoUpdate> list) {
    return Objects.nonNull(list)
      ? list.stream().map(Address::new).collect(Collectors.toList())
      : new ArrayList<Address>();
  }
}

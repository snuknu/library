package br.com.lawromm.library.dto.address;

import br.com.lawromm.library.model.Address;
import java.util.List;
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
public class AddressDtoDetail {
  private Long id;
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

  public AddressDtoDetail(Address address) {
    this(
      address.getId(),
      address.getAddressType(),
      address.getStreet(),
      address.getNumber(),
      address.getDistrict(),
      address.getCity(),
      address.getState(),
      address.getCountry(),
      address.getZipCode(),
      address.getReceiverName(),
      address.getReceiverPhone(),
      address.getComments()
    );
  }

  public static List<AddressDtoDetail> toList(List<Address> entityList) {
    return entityList.stream().map(AddressDtoDetail::new).collect(Collectors.toList());
  }
}

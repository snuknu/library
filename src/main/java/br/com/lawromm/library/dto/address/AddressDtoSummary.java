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
public class AddressDtoSummary {
  private Long id;
  private Integer addressType;
  private String street;
  private String city;

  public AddressDtoSummary(Address address) {
    this(
      address.getId(),
      address.getAddressType(),
      address.getStreet(),
      address.getCity()
    );
  }

  public static List<AddressDtoSummary> toList(List<Address> entityList) {
    return entityList.stream().map(AddressDtoSummary::new).collect(Collectors.toList());
  }
}

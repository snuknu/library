package br.com.lawromm.library.dto.publisher;

import jakarta.validation.constraints.NotBlank;
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
public class PublisherDtoCreate {
  @NotBlank
  private String name;

  @NotBlank
  private String city;

  @NotBlank
  private String uri;
}

package br.com.lawromm.library.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import br.com.lawromm.library.dto.publisher.PublisherDtoCreate;
import br.com.lawromm.library.dto.publisher.PublisherDtoUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String city;

  private String uri;

  public Publisher(PublisherDtoUpdate dto) {
    this.id = dto.getId();
    this.uri = dto.getUri();
  }

  public Publisher(PublisherDtoCreate dto) {
    this(null, dto.getName(), dto.getCity(), dto.getUri());
  }

  public Publisher update(PublisherDtoUpdate dto) {
    if (Objects.nonNull(dto.getUri())) this.uri = dto.getUri();
    return this;
  }

  public List<Publisher> toList(List<PublisherDtoUpdate> dtoList) {
    return dtoList.stream().map(Publisher::new).collect(Collectors.toList());
  }
}

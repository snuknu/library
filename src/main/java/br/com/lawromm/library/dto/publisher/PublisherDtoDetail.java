package br.com.lawromm.library.dto.publisher;

import br.com.lawromm.library.model.Publisher;
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
public class PublisherDtoDetail {
  private Long id;

  private String name;

  private String city;

  private String uri;

  public PublisherDtoDetail(Publisher entity) {
    this(entity.getId(), entity.getName(), entity.getCity(), entity.getUri());
  }

  public static List<PublisherDtoDetail> toList(List<Publisher> entityList) {
    return entityList.stream().map(PublisherDtoDetail::new).collect(Collectors.toList());
  }
}

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
public class PublisherDtoSummary {
  private Long id;

  private String name;

  public PublisherDtoSummary(Publisher author) {
    this(author.getId(), author.getName());
  }

  public static List<PublisherDtoSummary> toList(List<Publisher> entityList) {
    return entityList.stream().map(PublisherDtoSummary::new).collect(Collectors.toList());
  }
}

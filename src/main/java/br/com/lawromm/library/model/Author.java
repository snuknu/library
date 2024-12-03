package br.com.lawromm.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import br.com.lawromm.library.dto.author.AuthorDtoCreate;
import br.com.lawromm.library.dto.author.AuthorDtoUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String uri;

  @ManyToMany(mappedBy = "authorList")
  private List<Work> workList = new ArrayList<>();

  public Author(AuthorDtoCreate dto) {
    this.name = dto.getName();
    this.uri = dto.getUri();
  }

  public Author(AuthorDtoUpdate dto) {
    this(dto.getId(), dto.getName(), dto.getUri(), null);
  }

  public Author update(AuthorDtoUpdate dto) {
    if (Objects.nonNull(dto.getName())) this.name = dto.getName();
    if (Objects.nonNull(dto.getUri())) this.uri = dto.getUri();
    return this;
  }

  public static List<Author> toList(List<AuthorDtoUpdate> dtoList) {
    return dtoList.stream().map(Author::new).collect(Collectors.toList());
  }
}

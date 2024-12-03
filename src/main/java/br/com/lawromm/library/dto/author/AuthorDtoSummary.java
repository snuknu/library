package br.com.lawromm.library.dto.author;

import java.util.List;
import java.util.stream.Collectors;
import br.com.lawromm.library.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AuthorDtoSummary {
  @NotNull
  private Long id;

  @NotBlank
  private String name;

  public AuthorDtoSummary(Author author) {
    this(author.getId(), author.getName());
  }

  public static List<AuthorDtoSummary> toList(List<Author> entityList) {
    return entityList.stream().map(AuthorDtoSummary::new).collect(Collectors.toList());
  }
}

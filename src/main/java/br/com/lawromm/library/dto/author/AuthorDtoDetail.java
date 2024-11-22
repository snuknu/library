package br.com.lawromm.library.dto.author;

import br.com.lawromm.library.dto.work.WorkDtoSummary;
import br.com.lawromm.library.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AuthorDtoDetail {
  @NotNull
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String uri;

  private List<WorkDtoSummary> summaryWork;

  public AuthorDtoDetail(Author author) {
    this(
      author.getId(),
      author.getName(),
      author.getUri(),
      WorkDtoSummary.toList(author.getObras())
    );
  }

  public static List<AuthorDtoDetail> toList(List<Author> entityList) {
    return entityList.stream().map(AuthorDtoDetail::new).collect(Collectors.toList());
  }
}

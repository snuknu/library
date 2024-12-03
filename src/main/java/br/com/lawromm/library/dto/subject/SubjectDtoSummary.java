package br.com.lawromm.library.dto.subject;

import java.util.List;
import java.util.stream.Collectors;
import br.com.lawromm.library.model.Subject;
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
public class SubjectDtoSummary {
  @NotNull
  private Long id;

  @NotBlank
  private String description;

  public SubjectDtoSummary(Subject assunto) {
    this(assunto.getId(), assunto.getDescription());
  }

  public static List<SubjectDtoSummary> toList(List<Subject> entityList) {
    return entityList.stream().map(SubjectDtoSummary::new).collect(Collectors.toList());
  }
}

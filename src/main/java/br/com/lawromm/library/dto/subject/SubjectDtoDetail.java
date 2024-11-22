package br.com.lawromm.library.dto.subject;

import br.com.lawromm.library.dto.work.WorkDtoSummary;
import br.com.lawromm.library.model.Subject;
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
public class SubjectDtoDetail {
  @NotNull
  private Long id;

  @NotBlank
  private String description;

  private List<WorkDtoSummary> summaryWork;

  public SubjectDtoDetail(Subject entity) {
    this(
      entity.getId(),
      entity.getDescription(),
      WorkDtoSummary.toList(entity.getWorkList())
    );
  }

  public static List<SubjectDtoDetail> toList(List<Subject> entityList) {
    return entityList.stream().map(SubjectDtoDetail::new).collect(Collectors.toList());
  }
}

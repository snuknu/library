package br.com.lawromm.library.dto.work;

import java.util.List;
import br.com.lawromm.library.dto.subject.SubjectDtoUpdate;
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
public class WorkDtoUpdate {
  @NotNull
  private Long id;

  private String description;

  private List<SubjectDtoUpdate> subjectList;
}

package br.com.lawromm.library.dto.work;

import br.com.lawromm.library.dto.author.AuthorDtoSummary;
import br.com.lawromm.library.model.Work;
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
public class WorkDtoSummary {
  private Long id;
  private String title;
  private List<AuthorDtoSummary> authorList;

  public WorkDtoSummary(Work obra) {
    this(obra.getId(), obra.getTitle(), AuthorDtoSummary.toList(obra.getAuthorList()));
  }

  public static List<WorkDtoSummary> toList(List<Work> entityList) {
    return entityList.stream().map(WorkDtoSummary::new).collect(Collectors.toList());
  }
}

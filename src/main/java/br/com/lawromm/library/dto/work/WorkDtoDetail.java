package br.com.lawromm.library.dto.work;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.lawromm.library.dto.author.AuthorDtoSummary;
import br.com.lawromm.library.dto.publisher.PublisherDtoSummary;
import br.com.lawromm.library.dto.subject.SubjectDtoSummary;
import br.com.lawromm.library.enumeration.WorkType;
import br.com.lawromm.library.model.Work;
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
public class WorkDtoDetail {
  private Long id;

  private String isbn;

  private String title;

  private String description;

  private LocalDateTime yearPublication;

  private WorkType workType;

  private List<AuthorDtoSummary> authorList;

  private List<SubjectDtoSummary> subjectList;

  private PublisherDtoSummary publisher;

  public WorkDtoDetail(Work work) {
    this(
      work.getId(),
      work.getIsbn(),
      work.getTitle(),
      work.getDescription(),
      work.getYearPublication(),
      work.getWorkType(),
      AuthorDtoSummary.toList(work.getAuthorList()),
      SubjectDtoSummary.toList(work.getSubjectList()),
      new PublisherDtoSummary(work.getPublisher())
    );
  }

  public static List<WorkDtoDetail> toList(List<Work> entityList) {
    return entityList.stream().map(WorkDtoDetail::new).collect(Collectors.toList());
  }
}

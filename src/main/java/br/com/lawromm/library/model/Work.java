package br.com.lawromm.library.model;

import br.com.lawromm.library.dto.work.WorkDtoCreate;
import br.com.lawromm.library.dto.work.WorkDtoUpdate;
import br.com.lawromm.library.enumeration.WorkType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Entity
public class Work {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String isbn;

  private String title;

  private String description;

  private LocalDateTime yearPublication;

  @Enumerated(EnumType.ORDINAL)
  private WorkType workType;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @ManyToMany
  @JoinTable(
    name = "work_author",
    joinColumns = @JoinColumn(name = "work_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authorList;

  @ManyToMany
  @JoinTable(
    name = "work_subject",
    joinColumns = @JoinColumn(name = "work_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private List<Subject> subjectList;

  public Work(WorkDtoUpdate dto) {
    this.id = dto.getId();
  }

  public Work(Long id) {
    this.id = id;
  }

  public Work(WorkDtoCreate dto) {
    this(
      null,
      dto.getIsbn(),
      dto.getTitle(),
      dto.getDescription(),
      dto.getYearPublication(),
      dto.getWorkType(),
      new Publisher(dto.getPublisher()),
      Author.toList(dto.getAuthorList()),
      Subject.toList(dto.getSubjectList())
    );
  }

  public Work update(WorkDtoUpdate dto) {
    if (Objects.nonNull(dto.getDescription())) this.description = dto.getDescription();
    if (Objects.nonNull(dto.getSubjectList())) this.subjectList.addAll(
        Subject.toList(dto.getSubjectList())
      );
    return this;
  }

  public static List<Work> toList(List<WorkDtoUpdate> dtoList) {
    return Objects.nonNull(dtoList)
      ? dtoList.stream().map(Work::new).collect(Collectors.toList())
      : new ArrayList<Work>();
  }
}

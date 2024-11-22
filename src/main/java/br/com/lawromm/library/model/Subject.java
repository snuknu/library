package br.com.lawromm.library.model;

import br.com.lawromm.library.dto.subject.SubjectDtoCreate;
import br.com.lawromm.library.dto.subject.SubjectDtoUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "subjectList")
  private List<Work> obras = new ArrayList<>();

  public Subject(SubjectDtoCreate dto) {
    this.description = dto.getDescription();
  }

  public Subject(SubjectDtoUpdate dto) {
    this(dto.getId(), dto.getDescription(), null);
  }

  public Subject update(SubjectDtoUpdate dto) {
    if (Objects.nonNull(dto.getDescription())) this.description = dto.getDescription();
    return this;
  }

  public static List<Subject> toList(List<SubjectDtoUpdate> dtoList) {
    return dtoList.stream().map(Subject::new).collect(Collectors.toList());
  }
}

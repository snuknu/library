package br.com.lawromm.library.dto.work;

import java.time.LocalDateTime;
import java.util.List;
import br.com.lawromm.library.dto.author.AuthorDtoUpdate;
import br.com.lawromm.library.dto.publisher.PublisherDtoUpdate;
import br.com.lawromm.library.dto.subject.SubjectDtoUpdate;
import br.com.lawromm.library.enumeration.WorkType;
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
public class WorkDtoCreate {
  @NotBlank
  private String isbn;

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotNull
  private LocalDateTime yearPublication;

  @NotNull
  private WorkType workType;

  @NotNull
  private List<AuthorDtoUpdate> authorList;

  @NotNull
  private List<SubjectDtoUpdate> subjectList;

  @NotNull
  private PublisherDtoUpdate publisher;
}

package br.com.lawromm.library.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.lawromm.library.enumeration.SituacaoExemplar;
import br.com.lawromm.library.model.Exemplar;
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
public class DTOExemplar {
  private Long numExemplar;

  private Long obraCodigo;

  private LocalDateTime dataAquisicao;

  private SituacaoExemplar situacao;

  public DTOExemplar(Exemplar exemplar) {
    this(
      exemplar.getNumExemplar(),
      exemplar.getObraCodigo(),
      exemplar.getDataAquisicao(),
      exemplar.getSituacao()
    );
  }

  public static List<DTOExemplar> converter(List<Exemplar> exemplares) {
    return exemplares.stream().map(DTOExemplar::new).collect(Collectors.toList());
  }
}
